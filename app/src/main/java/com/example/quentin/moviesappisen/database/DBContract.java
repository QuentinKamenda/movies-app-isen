package com.example.quentin.moviesappisen.database;

import android.database.Cursor;

import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Movie;

import static android.provider.BaseColumns._ID;

/**
 * Created by Remi on 01/03/2018.
 */

public class DBContract {

    public static final String MOVIES_TABLE_NAME = "movies";
    public static final String TVSHOWS_TABLE_NAME = "tv_shows";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IMDB_ID = "imdb_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_OVERVIEW = "overview";
    public static final String COLUMN_POPULARITY = "popularity";
    public static final String COLUMN_RELEASE_DATE = "release_date";
    public static final String COLUMN_TRAILER = "trailer";

    public static Movie movieFromCursor(Cursor c){
        if (null != c){
            int id = c.getInt(c.getColumnIndex(COLUMN_ID));
            String title = c.getString(c.getColumnIndex(COLUMN_TITLE));

            final Movie movie = new Movie(id, title);

            // Retrieve the date of release
            if (c.getColumnIndex(COLUMN_RELEASE_DATE) >= 0){
                movie.release_date = c.getString(c.getColumnIndex(COLUMN_RELEASE_DATE));
            }

            // Retrieve the description
            if (c.getColumnIndex(COLUMN_OVERVIEW) >= 0){
                movie.overview = c.getString(c.getColumnIndex(COLUMN_OVERVIEW));
            }

            return movie;
        }
        return null;
    }
}
