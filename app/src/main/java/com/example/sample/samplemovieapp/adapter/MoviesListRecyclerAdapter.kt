package com.example.sample.samplemovieapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.sample.samplemovieapp.R
import com.example.sample.samplemovieapp.data.MovieModel
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by azimmemon on 17/10/19.
 */
class MoviesListRecyclerAdapter: RecyclerView.Adapter<MoviesListRecyclerAdapter.BaseViewHolder> {

    private val VIEW_TYPE_LOADING = 0
    private val VIEW_TYPE_NORMAL = 1
    private var isLoaderVisible = false


    var context: Context
    var movieList:MutableList<MovieModel.MovieResults>
    var searchList:ArrayList<MovieModel.MovieResults>
    var tileClickListener: OnMovieTitleClickListener? = null
    var TYPE: String

    constructor(context:Context, movieList:MutableList<MovieModel.MovieResults>, type: String){
        this.context = context
        this.movieList = movieList
        this.TYPE = type
        this.searchList = ArrayList()
        this.searchList.addAll(movieList)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BaseViewHolder {
        if (p1.equals(VIEW_TYPE_NORMAL))  return MoviesViewHolder(
                LayoutInflater.from(context).inflate(R.layout.movie_single_view, p0, false))
        else return ProgressHolder(
                LayoutInflater.from(context).inflate(R.layout.item_loading, p0, false))

    }

    fun setOnTileClickListener(tileClickListener: OnMovieTitleClickListener){
        this.tileClickListener = tileClickListener
    }

    override fun getItemCount(): Int {
        return movieList.size
    }


    override fun getItemViewType(position: Int): Int {
        return if (isLoaderVisible) {
            if (position == movieList.size - 1) VIEW_TYPE_LOADING else VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_NORMAL
        }
    }


    fun getItem(position: Int): MovieModel.MovieResults? {
        return movieList[position]
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
       holder.onBind(position, TYPE)
    }

   inner class MoviesViewHolder(view: View): BaseViewHolder(view) {
       override fun clear() {
       }

       override var mCurrentPosition: Int = 0
        var title:TextView
        var image:ImageView
        var favIcon:ImageView
        var deleteIcon:ImageView


        init {
            title = view.findViewById(R.id.movie_title)
            image = view.findViewById(R.id.movie_image)
            favIcon = view.findViewById(R.id.fav_icon)
            deleteIcon = view.findViewById(R.id.delete_icon)
        }


       override fun onBind(position: Int, type: String) {
           super.onBind(position, type)

           when(type){
               "MOVIES" -> {
                   favIcon.visibility = View.VISIBLE
                   deleteIcon.visibility = View.GONE
               }
               "FAVOURITE" -> {
                   favIcon.visibility = View.GONE
                   deleteIcon.visibility = View.VISIBLE
               }

           }

           title.text = movieList[position].getTitle()

           var imageUrl = "http://image.tmdb.org/t/p/original/${movieList[position].getPath()}"

           Picasso.with(context)
                   .load(imageUrl)
                   .resize(400,400).centerCrop()
                   .into(image)

           itemView.setOnClickListener({
               tileClickListener?.onClick(position)
           })

           favIcon.setOnClickListener({
               tileClickListener?.onFavButtonClick(movieList[position])
           })

       }


       override fun getCurrentPosition(): Int {
           return super.getCurrentPosition()
       }
    }

    class ProgressHolder(view: View): BaseViewHolder(view) {
        override fun clear() {
        }

    }


    abstract class BaseViewHolder (view: View): RecyclerView.ViewHolder(view) {

        open var mCurrentPosition = 0


        abstract fun clear()

        open fun onBind(position: Int, type:String) {
            mCurrentPosition = position
            clear()
        }

        open fun getCurrentPosition(): Int {
            return mCurrentPosition;
        }

    }

    open interface OnMovieTitleClickListener {
        fun onClick(position: Int)

        fun onFavButtonClick(movie: MovieModel.MovieResults)

    }

    fun filter(charText: String) {
        var charText = charText
        charText = charText.toLowerCase(Locale.getDefault())
        movieList.clear()
        if (charText.length == 0) {
            movieList.addAll(searchList)
        } else {
            for (s in searchList) {
                if (s.getTitle()?.toLowerCase(Locale.getDefault())!!.contains(charText)) {
                    movieList.add(s)
                }
            }
        }

        notifyDataSetChanged()
    }

}