package com.example.quentin.moviesappisen.TMDB;

import android.util.Log;

import com.example.quentin.moviesappisen.TMDB.TMDBAPIRequests.AbstractRequest;
import com.example.quentin.moviesappisen.TMDB.TMDBAPIRequests.QueryConfigs;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Collection;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Episode;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Movie;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.People;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Season;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.TVShow;

import java.util.ArrayList;

/**
 * Created by theo on 07/03/2018.
 */

public class DemoTMDBAPIRequests implements AbstractRequest.onObjectReceived, AbstractRequest.onSearchResultReceived, AbstractRequest.onDiscoverResultReceived  {


    public void start() {
        /*
        //instantiates a class to request infos on a specific object (Movie, TVShow, TVShow season, TVShow episode)
        QueryInfos request = new QueryInfos(this);
        request.getMovieDetails(123);
        request.getMovieDetails(19995);
        request.getMovieDetails(46512);
        request.getMovieDetails(45862);
        request.getTVShowDetails(456);
        request.getTVSeasonDetails(456, 2);
        request.getTVEpisodeDetails(456, 3, 6);
        */

        /*
        //instantiates a class to start search queries on Movies, TVShows, Collections and People
        QuerySearch search = new QuerySearch(this);
        search.searchMovies("Avatar", false, null, null, null, null, null);
        search.searchCollections("the lord of the rings", null, null);
        search.searchPeople("katy perry", false, null, null, null);
        search.searchTVShows("breaking bad", null, null, null);
        */

        /*
        QueryDiscover queryDiscover = new QueryDiscover(this);
        queryDiscover.getMovieDiscover(null, null , null);
        queryDiscover.getTVDiscover(null, null, null);
        */

        QueryConfigs queryConfigs = new QueryConfigs();
        /*
        queryConfigs.getBasicConfiguration();
        queryConfigs.getCountries();
        queryConfigs.getJobs();
        queryConfigs.getLanguages();
        queryConfigs.getTranslations();
        queryConfigs.getTimezones();
        */
        queryConfigs.getAllConfigs();


    }

    @Override
    public void onMovieDiscoverReceived(ArrayList<Movie> movies) {
        Log.d(this.getClass().getSimpleName(), movies.toString());
        //do things here
    }

    @Override
    public void onTVShowDiscoverReceived(ArrayList<TVShow> tvShows) {
        Log.d(this.getClass().getSimpleName(), tvShows.toString());
        //do things here
    }

    @Override
    public void onCollectionListReceived(ArrayList<Collection> collections) {
        Log.d(this.getClass().getSimpleName(), collections.toString());
        //do things here

    }

    @Override
    public void onMovieListReceived(ArrayList<Movie> movies) {
        Log.d(this.getClass().getSimpleName(), movies.toString());
        //do things here

    }

    @Override
    public void onPeopleListReceived(ArrayList<People> persons) {
        Log.d(this.getClass().getSimpleName(), persons.toString());
        //do things here

    }

    @Override
    public void onTVShowListReceived(ArrayList<TVShow> tvShows) {
        Log.d(this.getClass().getSimpleName(), tvShows.toString());
        //do things here

    }


    @Override
    public void onMovieReceived(Movie movie) {
        Log.d(this.getClass().getSimpleName(), movie.toString());
        //do things here

    }

    @Override
    public void onTVShowReceived(TVShow tvShow) {
        Log.d(this.getClass().getSimpleName(), tvShow.toString());
        //do things here

    }

    @Override
    public void onTVSeasonReceived(Season season) {
        Log.d(this.getClass().getSimpleName(), season.toString());
        //do things here

    }

    @Override
    public void onTVEpisodeReceived(Episode episode) {
        Log.d(this.getClass().getSimpleName(), episode.toString());
        //do things here

    }
}
