package com.example.sample.samplemovieapp.repository

import com.example.sample.samplemovieapp.data.MovieModel

/**
 * Created by azimmemon on 18/10/19.
 */
interface IMovieRepo {

    fun callApi(params:HashMap<String, String>, listener: OnFinishedListener)

    interface OnFinishedListener{
        fun onSuccess(movieData: MovieModel)
        fun onFailure(message:String)
    }
}