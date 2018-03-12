package com.example.quentin.moviesappisen;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Movie;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.TVShow;
import com.example.quentin.moviesappisen.fragments.MoviesFragment;
import com.example.quentin.moviesappisen.fragments.ShowsFragment;
import com.example.quentin.moviesappisen.interfaces.MovieListener;

import static com.example.quentin.moviesappisen.SearchMovieActivity.MOVIE_ID;
import static com.example.quentin.moviesappisen.SearchMovieActivity.SEARCH;

public class SearchShowActivity extends AppCompatActivity implements MovieListener, SearchView.OnQueryTextListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_show);
    }


    @Override
    public void onViewMovie(Movie movie) {

    }

    @Override
    public void onViewTVShow(TVShow show) {
        final Intent intent = new Intent(this, TVShowActivity.class);
        intent.putExtra(MOVIE_ID, show.id);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.
                getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {

        final ShowsFragment fragment = new ShowsFragment();
        final Bundle bundle = new Bundle();
        bundle.putString(SEARCH, s);
        fragment.setArguments(bundle);

        getFragmentManager().beginTransaction().add(R.id.container, fragment).commit();

        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
