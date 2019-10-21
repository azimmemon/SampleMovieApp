package com.example.sample.samplemovieapp.interactor

import android.arch.lifecycle.LiveData
import com.example.sample.samplemovieapp.data.MovieModel

/**
 * Created by azimmemon on 18/10/19.
 */
interface IMainPresenter {

    fun requestMoviesData(page: String)

    fun requestFavMovies():LiveData<MutableList<MovieModel.MovieResults>>

    fun addFavouriteMovie(movie:MovieModel.MovieResults)

}