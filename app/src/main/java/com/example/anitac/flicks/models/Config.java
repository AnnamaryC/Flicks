package com.example.anitac.flicks.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anitac on 6/28/18.
 */

public class Config {
    String imageBaseUrl; //showing images
    String posterSize; //fetch poster size
    String backdropSize; //fetch backdrop size


    public Config(JSONObject object) throws JSONException {
        JSONObject images = object.getJSONObject("images");

        //get image base url
        imageBaseUrl = images.getString("secure_base_url");

        //get poster size
        JSONArray posterSizeOptions = images.getJSONArray("poster_sizes")  ;
        posterSize = posterSizeOptions.optString(3, "w342")  ;

        //get backdrop size
        JSONArray BackdropSizeOptions = images.getJSONArray("backdrop_sizes")  ;
        backdropSize = posterSizeOptions.optString(3, "w780")  ;
    }

    public String getBackdropSize() {
        return backdropSize;
    }

    //helper method to construct url
    public String getImageURL(String size, String path){
        return String.format("%s%s%s",imageBaseUrl, size, path);

    }

    public String getImageBaseUrl() {
        return imageBaseUrl;
    }

    public String getPosterSize() {
        return posterSize;
    }
}
