package com.example.quentin.moviesappisen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quentin.moviesappisen.TMDB.TMDBAPIRequests.AbstractRequest;
import com.example.quentin.moviesappisen.TMDB.TMDBAPIRequests.QueryConfigs;
import com.example.quentin.moviesappisen.TMDB.TMDBAPIRequests.QueryInfos;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Episode;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Movie;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Season;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.TVShow;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Video;
import com.example.quentin.moviesappisen.async.DownloadTMDBImageQuery;

import static com.example.quentin.moviesappisen.SearchMovieActivity.MOVIE_ID;

public class MovieActivity extends AppCompatActivity implements AbstractRequest.onObjectReceived {

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
        infos.getMovieDetails(id);

        Button button = (Button) findViewById(R.id.home);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MovieActivity.this, HomeActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onMovieReceived(Movie movie) {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(movie.title);

        TextView year = (TextView) findViewById(R.id.year);
        year.setText(movie.release_date);

        TextView overview = (TextView) findViewById(R.id.overview);
        overview.setText(movie.overview);
        overview.setMovementMethod(new ScrollingMovementMethod());


        if(movie.poster_path != null) {
            Bitmap bitmap;
            ImageView poster = (ImageView) findViewById(R.id.moviePoster);
            if((bitmap = imageMemoryCache.getBitmapFromMemCache(movie.poster_path)) != null) {
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
                }, imageMemoryCache).execute(movie.poster_path, "w342");
            }
        }

        if(movie.videos != null) {
            boolean possessTrailer = false;
            for(Video video : movie.videos.videos) {
                if(video.type.equals("Trailer"))
                    possessTrailer = true;
            }

            if(possessTrailer) {
                String frameVideo = "<html><body>Video from Youtube<br><iframe width=\"420\" height=\"315\"" +
                        " src=\"https://www.youtube.com/embed/"+movie.videos.videos.get(0).key+"\" frameborder=\"0\" allowfullscreen></iframe></body></html>";

                WebView displayYoutubeVideo = (WebView) findViewById(R.id.trailer);
                displayYoutubeVideo.setWebViewClient(new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        return false;
                    }
                });
                WebSettings webSettings = displayYoutubeVideo.getSettings();
                webSettings.setJavaScriptEnabled(true);
                displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");
            }
        }
    }

    @Override
    public void onTVShowReceived(TVShow tvShow) {

    }

    @Override
    public void onTVSeasonReceived(Season season) {

    }

    @Override
    public void onTVEpisodeReceived(Episode episode) {

    }
}
