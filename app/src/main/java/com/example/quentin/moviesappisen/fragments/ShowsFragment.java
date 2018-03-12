package com.example.quentin.moviesappisen.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.quentin.moviesappisen.R;
import com.example.quentin.moviesappisen.TMDB.TMDBAPIRequests.AbstractRequest;
import com.example.quentin.moviesappisen.TMDB.TMDBAPIRequests.QuerySearch;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Collection;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Movie;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.People;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.TVShow;
import com.example.quentin.moviesappisen.adapters.ShowAdapter;
import com.example.quentin.moviesappisen.interfaces.MovieListener;

import java.util.ArrayList;

import static com.example.quentin.moviesappisen.SearchMovieActivity.SEARCH;

/**
 * Created by Remi on 12/03/2018.
 */

public class ShowsFragment extends Fragment implements AbstractRequest.onSearchResultReceived {

    private QuerySearch search;
    private ListView mListView;
    private MovieListener mListener;

    @Override
    public void onStart() {
        super.onStart();

        final String query = getArguments().getString(SEARCH);
        search = new QuerySearch(this);
        search.searchTVShows(query, null, null, null);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);

        mListView = (ListView) rootView.findViewById(R.id.moviesListView);

        final ProgressBar progressBar = new ProgressBar(getActivity());
        progressBar.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        mListView.setEmptyView(progressBar);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(null != mListener){
                    final TVShow show = (TVShow) adapterView.getItemAtPosition(i);
                    mListener.onViewTVShow(show);
                }
            }
        });

        ViewGroup root = (ViewGroup) rootView.findViewById(R.id.moviesRootRelativeLayout);
        root.addView(progressBar);

        return rootView;
    }

    @Override
    public void onCollectionListReceived(ArrayList<Collection> collections) {

    }

    @Override
    public void onMovieListReceived(ArrayList<Movie> movies) {

    }

    @Override
    public void onPeopleListReceived(ArrayList<People> persons) {

    }

    @Override
    public void onTVShowListReceived(ArrayList<TVShow> tvShows) {
        final ShowAdapter adapter = new ShowAdapter(tvShows, getActivity());
        mListView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof MovieListener)
            mListener = (MovieListener) activity;
    }
}
