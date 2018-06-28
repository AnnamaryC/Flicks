package com.example.anitac.flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.anitac.flicks.models.MovieData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieListActivity extends AppCompatActivity {



    //base url API
    public final static String API_BASE_URL = "https://api.themoviedb.org/3";
    public final static String API_KEY_param = "api_key"; //parameter name api key
    public final static String TAG = "MovieListActivity"; //tag for logging from this activity

    //intance field
    AsyncHttpClient client;
    String imageBaseUrl; //showing images
    String posterSize; //fetch poster size
    ArrayList<MovieData> movies;  //list of currently playing movies
    RecyclerView rvMovies; //recycler view
    movieAdapter adapter;



    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        client = new AsyncHttpClient(); //created every time we want to make an api call
        movies =  new ArrayList<>();

        //get the configuration
        GetConfiguration();

        GetNowpLaying();
    }

    //get list of currently plaing movies
    private void GetNowpLaying(){
        String url = API_BASE_URL + "/movie/now_playing"; //get url
                                                                
        //set request parameters                                
        RequestParams params = new RequestParams();             
        params.put(API_KEY_param, getString(R.string.api_key));

        client.get(url, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");
                    //pass to movie constructor

                    for(int i=0; i < results.length(); i++){

                        MovieData movie = new MovieData(results.getJSONObject(i));
                        movies.add(movie);
                    }

                    Log.i(TAG, String.format("Loaded %s movies", results.length()));

                } catch (JSONException e) {
                    LogError("Failed to parse now playing movies.", e, true);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                LogError("Failed to get data from now_playing.", throwable, true);
            }
        });

    }

    private void GetConfiguration(){
        String url = API_BASE_URL + "/configuration"; //get url

        //set request parameters
        RequestParams params = new RequestParams();
        params.put(API_KEY_param, getString(R.string.api_key));

        //excecute a GET method
        client.get(url, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONObject images = response.getJSONObject("images");

                    //get image base url
                    imageBaseUrl = images.getString("secure_base_url");

                    //get poster size
                    JSONArray posterSizeOptions = images.getJSONArray("poster_sizes")  ;
                    posterSize = posterSizeOptions.optString(3, "w342")  ;

                    Log.i(TAG, String.format("Loaded configuration with imageBaseUrl %s and posterSize %s", imageBaseUrl, posterSize));
                } catch (JSONException e) {
                    LogError("Fail parsing configuration.", e ,true);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                LogError("Error getting configuration.", throwable, true);
            }
        });
    }

    //handle error, log and alert error, allows users to use error
    private void LogError(String message, Throwable error, boolean alertuser){
        Log.e(TAG, message, error);
        if(alertuser){  //use long toast for user to see it
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }
}
