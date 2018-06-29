package com.example.anitac.flicks;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.anitac.flicks.models.MovieData;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity {

    MovieData movie;


    @BindView(R.id.tvTitle) TextView title;
    @BindView(R.id.tvOverview) TextView overview;
    @BindView(R.id.rbVoteAverage) RatingBar ratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        // unwrap the movie passed in via intent, using its simple name as a key

        ButterKnife.bind(this);

        movie = (MovieData) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());

        //for ratings
        float voteAverage = movie.getVoteAverage().floatValue();
        ratings.setRating(voteAverage = voteAverage > 0 ? voteAverage / 2.0f : voteAverage);
    }
}
