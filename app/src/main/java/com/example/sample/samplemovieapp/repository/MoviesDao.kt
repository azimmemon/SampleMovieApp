package com.example.sample.samplemovieapp.repository

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.sample.samplemovieapp.data.MovieEntityDB
import com.example.sample.samplemovieapp.data.MovieModel

/**
 * Created by azimmemon on 18/10/19.
 */
@Dao
interface MoviesDao {

    @Insert
    fun addMovie(movie: MovieModel.MovieResults)

    @Query("SELECT * FROM movies")
    fun getFavouriteMovies(): LiveData<MutableList<MovieModel.MovieResults>>

    @Query("DELETE FROM movies WHERE movieId = :movieId")
    fun deleteMovie(movieId: Int)
}