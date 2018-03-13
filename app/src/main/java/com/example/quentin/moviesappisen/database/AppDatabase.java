package com.example.quentin.moviesappisen.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Movie;

/**
 * Created by Remi on 01/03/2018.
 */

@Database(entities = {Movie.class}, version=1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
}
