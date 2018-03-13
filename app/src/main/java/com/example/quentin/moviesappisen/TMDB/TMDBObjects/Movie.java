package com.example.quentin.moviesappisen.TMDB.TMDBObjects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

/**
 * Created by theo on 01/03/2018.
 */

@Entity(tableName = "movies")
public class Movie {

    @PrimaryKey
    public final int id;
    @ColumnInfo
    public String imdb_id;
    @ColumnInfo
    public String title;
    @ColumnInfo
    public String overview;
    @ColumnInfo
    public float popularity;
    @ColumnInfo
    public String release_date;
    @ColumnInfo
    public boolean video;
    @Ignore
    public boolean adult;
    @Ignore
    public String backdrop_path;
    @Ignore
    public Collection belongs_to_collection;
    @Ignore
    public Long budget;
    @Ignore
    public ArrayList<Genre> genres = new ArrayList<Genre>();
    @Ignore
    public String homepage;
    @Ignore
    public ArrayList<Country> production_countries = new ArrayList<Country>();
    @Ignore
    public String original_language;
    @Ignore
    public String original_title;
    @Ignore
    public String poster_path;
    @Ignore
    public ArrayList<Company> production_companies = new ArrayList<Company>();
    @Ignore
    public Long revenue;
    @Ignore
    public int runtime;
    @Ignore
    public ArrayList<Language> spoken_languages = new ArrayList<Language>();
    @Ignore
    public String status;
    @Ignore
    public String tagline;
    @Ignore
    public float vote_average;
    @Ignore
    public float vote_count;
    @Ignore
    public Videos videos;


    public Movie(int id, String title) {
        this.id = id;
        this.title = title;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return id == movie.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "adult=" + adult +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", belongs_to_collection=" + belongs_to_collection +
                ", budget=" + budget +
                ", genres=" + genres +
                ", homepage='" + homepage + '\'' +
                ", id=" + id +
                ", production_countries=" + production_countries +
                ", imdb_id='" + imdb_id + '\'' +
                ", original_language='" + original_language + '\'' +
                ", original_title='" + original_title + '\'' +
                ", overview='" + overview + '\'' +
                ", popularity=" + popularity +
                ", poster_path='" + poster_path + '\'' +
                ", production_companies=" + production_companies +
                ", release_date='" + release_date + '\'' +
                ", revenue=" + revenue +
                ", runtime=" + runtime +
                ", spoken_languages=" + spoken_languages +
                ", status='" + status + '\'' +
                ", tagline='" + tagline + '\'' +
                ", title='" + title + '\'' +
                ", video=" + video +
                ", vote_average=" + vote_average +
                ", vote_count=" + vote_count +
                ", videos=" + videos +
                '}';
    }
}
