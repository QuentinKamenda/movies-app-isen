package com.example.quentin.moviesappisen.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.quentin.moviesappisen.R;
import com.example.quentin.moviesappisen.TMDB.TMDBAPIRequests.QueryConfigs;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.Movie;
import com.example.quentin.moviesappisen.TMDB.TMDBObjects.TVShow;
import com.example.quentin.moviesappisen.async.DownloadTMDBImageQuery;

import java.util.ArrayList;

/**
 * Created by Remi on 12/03/2018.
 */

public class ShowAdapter extends BaseAdapter {

    private ArrayList<TVShow> shows;
    private LayoutInflater mInflater;

    public ShowAdapter(ArrayList<TVShow> shows, Context context) {
        this.shows = shows;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return null != shows ? shows.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return null != shows ? shows.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;

        if(null == view){
            view = mInflater.inflate(R.layout.filmlist_adapter, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        final TVShow show = (TVShow) getItem(i);

        holder.title.setText(show.name);
        holder.year.setText(show.first_air_date);

        QueryConfigs configs = new QueryConfigs();
        configs.getBasicConfiguration();

        DownloadTMDBImageQuery imageQuery = new DownloadTMDBImageQuery(new DownloadTMDBImageQuery.onImageReceived() {
            @Override
            public void processBitmap(Bitmap bitmap) {
                holder.poster.setImageBitmap(bitmap);
            }
        });
        imageQuery.execute(show.poster_path, "w92");

        return view;
    }
}
