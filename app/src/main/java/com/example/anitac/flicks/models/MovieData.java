package com.example.anitac.flicks.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

/**
 * Created by anitac on 6/27/18.
 */

@Parcel // makes class parcelable
public class MovieData {
    private String title;
    private String overview;
    private String posterPath; //not full url, just path
    private String backdropPath;
    private Double voteAverage;

    public MovieData(){}

    public MovieData(JSONObject object){
        try {
            title = object.getString("title");
            overview = object.getString("overview");
            posterPath = object.getString("poster_path");
            backdropPath = object.getString("backdrop_path");
            voteAverage = object.getDouble("vote_average");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
