package com.example.quentin.moviesappisen.interfaces;

import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Movie;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.TVShow;

/**
 * Created by Remi on 08/03/2018.
 */

public interface MovieListener {

    public void onViewMovie(Movie movie);
    public void onViewTVShow(TVShow show);
}
