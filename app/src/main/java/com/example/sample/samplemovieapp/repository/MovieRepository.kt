package com.example.sample.samplemovieapp.repository

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import android.util.Log
import com.example.sample.samplemovieapp.App
import com.example.sample.samplemovieapp.api.ApiInterface
import com.example.sample.samplemovieapp.data.MovieEntityDB
import com.example.sample.samplemovieapp.data.MovieModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by azimmemon on 18/10/19.
 */
class MovieRepository: IMovieRepo{

    var database: MovieDatabase
    var moviesDao: MoviesDao

    constructor(){
        database = App.getDatabase(MovieDatabase::class.java, "movies_database")
        moviesDao = database.movieDao()
    }

    override fun callApi(params: HashMap<String, String>, listener: IMovieRepo.OnFinishedListener) {
        var retrofit = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var apiInterface = retrofit.create(ApiInterface::class.java)
        var callResponse = apiInterface.getMoviesList(params)
        callResponse.enqueue(object: Callback<MovieModel>{
            override fun onResponse(call: Call<MovieModel>?, response: Response<MovieModel>) {
                listener.onSuccess(response.body())

            }

            override fun onFailure(call: Call<MovieModel>?, t: Throwable?) {
                Log.d("RESPONSE", t?.message)

            }
        })


    }

    fun getFavouriteMovies(): LiveData<MutableList<MovieModel.MovieResults>>{
        return moviesDao.getFavouriteMovies()
    }


    fun insertFavMovie(movieEntityDB: MovieModel.MovieResults){
        InsertFavouriteMovie(moviesDao).execute(movieEntityDB)
    }

    fun deleteFavMovie(id: Int){
//        DeleteMovie(moviesDao).execute(id)
    }


    class InsertFavouriteMovie(var moviesDao: MoviesDao): AsyncTask<MovieModel.MovieResults, Unit, Unit>(){

        override fun doInBackground(vararg params: MovieModel.MovieResults) {
            moviesDao.addMovie(params[0])

        }

    }

//    class DeleteMovie(var moviesDao: MoviesDao): AsyncTask<Int, Unit, Unit>(){
//        override fun doInBackground(vararg params: Int) {
//
//            moviesDao.deleteMovie(params[0])
//        }
//
//    }


}