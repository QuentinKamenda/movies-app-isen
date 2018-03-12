package com.example.quentin.moviesappisen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quentin.moviesappisen.TMDB.TMDBAPIRequests.AbstractRequest;
import com.example.quentin.moviesappisen.TMDB.TMDBAPIRequests.QueryConfigs;
import com.example.quentin.moviesappisen.TMDB.TMDBAPIRequests.QueryDiscover;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Movie;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.TVShow;
import com.example.quentin.moviesappisen.async.DownloadTMDBImageQuery;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements AbstractRequest.onDiscoverResultReceived {

    private QueryDiscover discover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        discover = new QueryDiscover(this);
        discover.getMovieDiscover("popularity.desc", null, null);
        discover.getTVDiscover("popularity.desc", null, null);
    }

    @Override
    public void onMovieDiscoverReceived(ArrayList<Movie> movies) {
        QueryConfigs configs = new QueryConfigs();
        configs.getBasicConfiguration();

        for(int i = 0; i < 3; i++){
            TextView title = (TextView) findViewById(getResources().getIdentifier("movieTrendTitle" + (i+1),
                    "id", getPackageName()));
            title.setText(movies.get(i).title);

            final int id = i;

            new DownloadTMDBImageQuery(new DownloadTMDBImageQuery.onImageReceived() {
                @Override
                public void processBitmap(Bitmap bitmap) {
                    ImageView poster = (ImageView) findViewById(getResources().getIdentifier("movieTrendPoster" + (id+1),
                            "id", getPackageName()));
                    poster.setImageBitmap(bitmap);
                }
            }).execute(movies.get(i).poster_path, "w92");
        }
    }

    @Override
    public void onTVShowDiscoverReceived(ArrayList<TVShow> tvShows) {
        for(int i = 0; i < 3; i++){
            TextView title = (TextView) findViewById(getResources().getIdentifier("showTrendTitle" + (i+1),
                    "id", getPackageName()));
            title.setText(tvShows.get(i).name);

            final int id = i;

            new DownloadTMDBImageQuery(new DownloadTMDBImageQuery.onImageReceived() {
                @Override
                public void processBitmap(Bitmap bitmap) {
                    ImageView poster = (ImageView) findViewById(getResources().getIdentifier("showTrendPoster" + (id+1),
                            "id", getPackageName()));
                    poster.setImageBitmap(bitmap);
                }
            }).execute(tvShows.get(i).poster_path, "w92");
        }
    }

    public void searchMovies(View view){
        startActivity(new Intent(this, SearchMovieActivity.class));
    }

    public void searchShows(View view){
        startActivity(new Intent(this, SearchShowActivity.class));
    }
}
