package com.example.sample.samplemovieapp.presenter

import android.arch.lifecycle.LiveData
import com.example.sample.samplemovieapp.data.MovieModel
import com.example.sample.samplemovieapp.interactor.IMainPresenter
import com.example.sample.samplemovieapp.interactor.IMainView
import com.example.sample.samplemovieapp.repository.IMovieRepo
import com.example.sample.samplemovieapp.repository.MovieRepository
import com.example.sample.samplemovieapp.utils.Constants

/**
 * Created by azimmemon on 18/10/19.
 */
class MainPresenter: IMainPresenter, IMovieRepo.OnFinishedListener{


    var movieRepository: MovieRepository
    lateinit var mainView: IMainView

    constructor(){
//        this.mainView = mainView
        movieRepository = MovieRepository()
    }


    fun setView(mainView: IMainView){
        this.mainView = mainView
    }

    override fun requestMoviesData(page: String) {
        var params = HashMap<String, String>()
        params.put(Constants.PRIMARY_RELEASE_YEAR, "2019")
        params.put(Constants.SORT_BY, "popularity.desc")
        params.put(Constants.API_KEY, "faf9017f727d442e04664ab7fc4cdbc5")
        params.put(Constants.PAGE, page)

        movieRepository.callApi(params, this)

    }


    override fun onSuccess(movieData: MovieModel) {
        mainView.onDataFetchSuccess(movieData)
    }

    override fun onFailure(message: String) {
        mainView.onDataFetchFailure(message)

    }

    override fun requestFavMovies():LiveData<MutableList<MovieModel.MovieResults>> {
        return movieRepository.getFavouriteMovies()
    }

    override fun addFavouriteMovie(movie: MovieModel.MovieResults) {

        movieRepository.insertFavMovie(movie)
    }

}