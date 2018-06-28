package com.example.anitac.flicks.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anitac on 6/27/18.
 */

public class MovieData {
    private String title;
    private String overview;
    private String posterPath; //not full url, just path


    public MovieData(JSONObject object){
        try {
            title = object.getString("title");
            overview = object.getString("overview");
            posterPath = object.getString("poster_path");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
