package com.example.sample.samplemovieapp.api

import com.example.sample.samplemovieapp.data.MovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by azimmemon on 18/10/19.
 */
interface ApiInterface {

    @GET("/3/discover/movie/")

    fun getMoviesList(@QueryMap params: HashMap<String, String>):Call<MovieModel>

}