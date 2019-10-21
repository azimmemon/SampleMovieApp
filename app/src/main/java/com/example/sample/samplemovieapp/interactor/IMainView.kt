package com.example.sample.samplemovieapp.interactor

import com.example.sample.samplemovieapp.data.MovieModel

/**
 * Created by azimmemon on 18/10/19.
 */
interface IMainView {

    fun onDataFetchSuccess(data: MovieModel)

    fun onDataFetchFailure(message:String)
}