package com.example.anitac.flicks;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anitac.flicks.models.Config;
import com.example.anitac.flicks.models.GlideApp;
import com.example.anitac.flicks.models.MovieData;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by anitac on 6/27/18.
 */

public class movieAdapter extends RecyclerView.Adapter<movieAdapter.ViewHolder>{
    //list of movies
    ArrayList<MovieData> movies;
    Context context; //context for rendering images
    Config config; //configuration to get image url

    public movieAdapter(ArrayList<MovieData> movies) {

        this.movies = movies;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    //creates and inflates new view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //get context and creater inflater
        context =  parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //creater view
        View movieView = inflater.inflate(R.layout.item_movie, parent, false);

        //return view by viewholder
        return new ViewHolder(movieView);
    }

    //binds an inflated view to a new item
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieData movie = movies.get(position);

        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());

        //build url
        String imageURL = config.getImageURL(config.getPosterSize(), movie.getPosterPath());

        //load image using glide (external library)
        GlideApp.with(context)
                .load(imageURL)
                .placeholder(R.drawable.flicks_movie_placeholder)
                .transform(new RoundedCornersTransformation(25, 0))
                .error(R.drawable.flicks_movie_placeholder)
                .into(holder.ivPosterImage);


    }

    //return size of entire data set
    @Override
    public int getItemCount() {
        return movies.size();
    }

    //create view holder as static inner class
    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPosterImage;
        TextView tvTitle;
        TextView tvOverview;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPosterImage =(ImageView) itemView.findViewById(R.id.posterImage);
            tvOverview = (TextView) itemView.findViewById(R.id.MovieOverview);
            tvTitle = (TextView) itemView.findViewById(R.id.MovieTitle);

        }
    }{

    }
}
