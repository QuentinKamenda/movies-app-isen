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
import com.example.quentin.moviesappisen.interfaces.MovieListener;


public class SearchMovieActivity extends AppCompatActivity implements MovieListener, SearchView.OnQueryTextListener {

    public final static String MOVIE_ID = "com.example.quentin.moviesappisen.ID";
    public final static String SEARCH = "com.example.quentin.moviesappisen.SEARCH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_movie);
    }


    @Override
    public void onViewMovie(Movie movie) {
        final Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtra(MOVIE_ID, movie.id);

        startActivity(intent);
    }

    @Override
    public void onViewTVShow(TVShow show){}

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

        final MoviesFragment fragment = new MoviesFragment();
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
