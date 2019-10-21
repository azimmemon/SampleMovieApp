package com.example.sample.samplemovieapp.repository

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.sample.samplemovieapp.data.MovieEntityDB
import com.example.sample.samplemovieapp.data.MovieModel

/**
 * Created by azimmemon on 18/10/19.
 */
@Database(entities = arrayOf(MovieModel.MovieResults::class), version = 1)

abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MoviesDao
}