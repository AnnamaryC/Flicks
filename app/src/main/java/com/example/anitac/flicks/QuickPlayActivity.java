package com.example.anitac.flicks;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class QuickPlayActivity extends YouTubeBaseActivity {

    public final static String API_BASE_URL = "https://api.themoviedb.org/3/movie/";
    public final static String API_KEY_param = "api_key_y"; //parameter name api key
    public final static String TAG = "QuickPlayActivity";
    public final static String LANGUAGE = "en_US";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_base);
        YouTubePlayerView playerView = findViewById(R.id.videoView);

        playerView.initialize(API_KEY_param, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("ss");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}
