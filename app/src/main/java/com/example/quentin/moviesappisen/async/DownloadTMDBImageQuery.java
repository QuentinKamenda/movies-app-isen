package com.example.quentin.moviesappisen.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.example.quentin.moviesappisen.TMDB.Configuration;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by theo on 07/03/2018.
 */

public class DownloadTMDBImageQuery extends AsyncTask<String, Void, Bitmap> {

    /**
     * interface to implement for the caller to be given the image
     */
    public interface onImageReceived {
        public void processBitmap(Bitmap bitmap);
    }

    private onImageReceived mListener;



    public DownloadTMDBImageQuery(onImageReceived listener) {
        mListener = listener;
    }

    /**
     * request the image from TMDB servers
     * @param imageUrl  first argument must be the path of the image in the form /<hexa>.png, second argument can be the size to take (see valid sizes in {@link Configuration().image_sizes} but is optional (default is 'original' size)
     * @return
     */
    @Override
    protected Bitmap doInBackground(String... imageUrl) {
        String request = prepareRequest(imageUrl);

        if(request == null)
            return null;

        URL requestUrl;
        HttpURLConnection connection;


        try {
            requestUrl = new URL(request);
            Log.d(this.getClass().getSimpleName(), "request : " + requestUrl.toString());

            connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            Log.d(this.getClass().getSimpleName(), "response code : " + Integer.toString(responseCode));
            if (responseCode == 200){
                final Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        mListener.processBitmap(bitmap);
    }

    private String prepareRequest(String... imageUrl) {
        if(Configuration.image_sizes.base_url != null || Configuration.image_sizes.secure_base_url != null) {
            String url;
            String size;

            if(!Configuration.image_sizes.secure_base_url.isEmpty())
                url = Configuration.image_sizes.secure_base_url;
            else if(!Configuration.image_sizes.base_url.isEmpty())
                url = Configuration.image_sizes.base_url;
            else
                return null;

            if(imageUrl.length > 1 && Configuration.image_sizes.backdrop_sizes != null)
                if(
                        Configuration.image_sizes.backdrop_sizes.contains(imageUrl[1]) ||
                                Configuration.image_sizes.logo_sizes.contains(imageUrl[1]) ||
                                Configuration.image_sizes.poster_sizes.contains(imageUrl[1]) ||
                                Configuration.image_sizes.profile_sizes.contains(imageUrl[1]) ||
                                Configuration.image_sizes.still_sizes.contains(imageUrl))
                    size = imageUrl[1];
                else
                    size = "original";
            else
                size = "original";

            if(imageUrl[0] != null && imageUrl[0].length() > 0)
                return url + size + imageUrl[0];
        }

        return null;
    }
}

