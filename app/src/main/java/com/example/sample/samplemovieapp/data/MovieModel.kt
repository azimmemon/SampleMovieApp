package com.example.sample.samplemovieapp.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by azimmemon on 17/10/19.
 */

data class MovieModel(@SerializedName("results") var results:MutableList<MovieResults>,
                      @SerializedName("total_pages")
                      var total_pages: String): Serializable {
    @Entity(tableName = "movies")
    class MovieResults: Serializable{
        @PrimaryKey(autoGenerate = true)
        private var _id:Int = 0
        @SerializedName("id")
        private var movieId: Int = 0
        private var title:String? = null
        @SerializedName("poster_path")
        private var path:String? = null
        private var overview: String? = null
        private var popularity: String? = null
        private var adult: String? = null


        fun setId(id:Int){
            this._id = id
        }

        fun getId():Int{
            return _id
        }

        fun setMovieId(movieId: Int){
            this.movieId = movieId
        }

        fun getMovieId():Int{
            return movieId
        }

        fun getTitle():String?{
            return title
        }

        fun setTitle(title: String){
            this.title = title
        }

        fun setPath(path:String?){
            this.path = path
        }


        fun getPath():String?{
            return path
        }


        fun getOverview():String?{
            return overview
        }

        fun setOverview(overview: String){
            this.overview = overview
        }

        fun getPopularity():String?{
            return popularity
        }

        fun setPopularity(popularity: String){
            this.popularity = popularity
        }

        fun setAdult(adult: String){
            this.adult = adult
        }

        fun getAdult():String?{
            return adult
        }
    }




}
