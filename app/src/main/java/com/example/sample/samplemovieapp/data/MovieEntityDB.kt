package com.example.sample.samplemovieapp.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by azimmemon on 18/10/19.
 */

@Entity(tableName = "movies")
class MovieEntityDB {

    @PrimaryKey(autoGenerate = true)
    private var id: Int = 0

    private var movieId: Int = 0

    private var title: String? = null

    private var imagePath: String? = null


    fun setTitle(title:String){
        this.title = title
    }

    fun getTitle(): String{
        return title!!
    }

    fun  setImagePath(path:String){
        this.imagePath = path
    }

    fun getImagePath(): String{
        return imagePath!!
    }


    fun setMovieId(movieId:Int){
        this.movieId = movieId
    }

    fun getMovieId():Int{
        return movieId
    }



}