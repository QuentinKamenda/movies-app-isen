package com.example.quentin.moviesappisen.database;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Movie;

import java.util.List;

/**
 * Created by Remi on 01/03/2018.
 */

@android.arch.persistence.room.Dao
public interface MovieDao {

    @Query("SELECT * FROM " + DBContract.MOVIES_TABLE_NAME)
    List<Movie> getAllMovies();

    @Query("SELECT * FROM " + DBContract.MOVIES_TABLE_NAME + " WHERE " + DBContract.COLUMN_ID + "=:id")
    Movie getMovieById(long id);

    @Query("SELECT * FROM " + DBContract.MOVIES_TABLE_NAME + " WHERE " + DBContract.COLUMN_IMDB_ID + "=:id")
    Movie getMovieByImdbId(String id);

    @Insert
    long insertMovie(Movie movie);

    @Update
    int updateMovie(Movie movie);

    @Delete
    int deleteMovie(Movie movie);

    @Query("DELETE FROM " + DBContract.MOVIES_TABLE_NAME + " WHERE " + DBContract.COLUMN_ID + "=:id")
    int deleteMovieById(int id);
}
