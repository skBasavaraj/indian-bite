package com.bsk.indianbite

import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class VideoPlayActivity : YouTubeBaseActivity() {
    lateinit var playerView: YouTubePlayerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_play)
        var selectedVideoID = intent.getStringExtra("VIDEO_ID")
        val KEY = "AIzaSyBMkGzbCoxXfwhElvPk-tt4HPIINl_ju50"
        playerView = findViewById(R.id.youtube_playerview)
        playerView.initialize(KEY, object : YouTubePlayer.OnInitializedListener {

            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider,
                youTubePlayer: YouTubePlayer,
                restored: Boolean
            ) {
                if (!restored) {
                    youTubePlayer.cueVideo(selectedVideoID)
                }
            }

            override fun onInitializationFailure(
                provider: YouTubePlayer.Provider,
                youTubeInitializationResult: YouTubeInitializationResult
            ) {
                Toast.makeText(applicationContext, "Something went wrong..!", Toast.LENGTH_SHORT)
                    .show()
            }
        })

    }


}