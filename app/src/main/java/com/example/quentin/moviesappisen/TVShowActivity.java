package com.example.quentin.moviesappisen;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quentin.moviesappisen.TMDB.TMDBAPIRequests.AbstractRequest;
import com.example.quentin.moviesappisen.TMDB.TMDBAPIRequests.QueryConfigs;
import com.example.quentin.moviesappisen.TMDB.TMDBAPIRequests.QueryInfos;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Episode;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Movie;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Season;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.TVShow;
import com.example.quentin.moviesappisen.async.DownloadTMDBImageQuery;

import static com.example.quentin.moviesappisen.SearchMovieActivity.MOVIE_ID;

public class TVShowActivity extends AppCompatActivity implements AbstractRequest.onObjectReceived {

    private QueryInfos infos;

    private ImageMemoryCache imageMemoryCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie);

        final int id = getIntent().getIntExtra(MOVIE_ID, 0);

        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cache = maxMemory / 20;
        imageMemoryCache = new ImageMemoryCache(cache);

        infos = new QueryInfos(this);
        infos.getTVShowDetails(id);
    }

    @Override
    public void onMovieReceived(Movie movie) {

    }

    @Override
    public void onTVShowReceived(TVShow tvShow) {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(tvShow.name);

        TextView year = (TextView) findViewById(R.id.year);
        year.setText(tvShow.first_air_date);

        TextView overview = (TextView) findViewById(R.id.overview);
        overview.setText(tvShow.overview);

        QueryConfigs configs = new QueryConfigs();
        configs.getBasicConfiguration();

        Bitmap bitmap;
        ImageView poster = (ImageView) findViewById(R.id.moviePoster);
        if((bitmap = imageMemoryCache.getBitmapFromMemCache(tvShow.poster_path)) != null) {
            poster.setImageBitmap(bitmap);
        }
        else
        {
            new DownloadTMDBImageQuery(new DownloadTMDBImageQuery.onImageReceived() {
                @Override
                public void processBitmap(Bitmap bitmap) {
                    ImageView poster = (ImageView) findViewById(R.id.moviePoster);
                    poster.setImageBitmap(bitmap);
                }
            }, imageMemoryCache).execute(tvShow.poster_path, "w500");
        }

    }

    @Override
    public void onTVSeasonReceived(Season season) {

    }

    @Override
    public void onTVEpisodeReceived(Episode episode) {

    }
}
