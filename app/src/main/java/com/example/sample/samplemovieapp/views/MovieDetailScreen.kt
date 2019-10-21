package com.example.sample.samplemovieapp.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sample.samplemovieapp.R
import com.example.sample.samplemovieapp.data.MovieModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail_screen.*

class MovieDetailScreen : AppCompatActivity() {

    lateinit var movie: MovieModel.MovieResults

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail_screen)

        var bundle = intent.extras
        if (bundle != null){
            movie = bundle.get("movie") as MovieModel.MovieResults
        }

        var imageUrl = "http://image.tmdb.org/t/p/original/${movie.getPath()}"
        Log.d("RESPONSE", movie.getTitle())
        Picasso.with(this@MovieDetailScreen)
                .load(imageUrl)
                .resize(500,400).centerCrop()
                .into(movie_image)

        movie_title.text = movie.getTitle()
        adult.text = movie.getAdult()
        overview.text = movie.getOverview()

    }
}
