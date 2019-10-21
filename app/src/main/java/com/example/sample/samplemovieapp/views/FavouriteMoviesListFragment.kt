package com.example.sample.samplemovieapp.views


import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.sample.samplemovieapp.R
import com.example.sample.samplemovieapp.adapter.MoviesListRecyclerAdapter
import com.example.sample.samplemovieapp.data.MovieModel
import com.example.sample.samplemovieapp.presenter.MainPresenter
import kotlinx.android.synthetic.main.fragment_favourite_movies_list.view.*


/**
 * A simple [Fragment] subclass.
 */
class FavouriteMoviesListFragment : Fragment(), MoviesListRecyclerAdapter.OnMovieTitleClickListener{


    var favList: MutableList<MovieModel.MovieResults>? = null
    lateinit var mPresenter: MainPresenter
    lateinit var mAdapter: MoviesListRecyclerAdapter
    var TYPE  = "FAVOURITE"



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_favourite_movies_list, container, false)

        view.movies_recycler_view.layoutManager = GridLayoutManager(activity, 2)
        mPresenter = MainPresenter()
        mPresenter.requestFavMovies().observe(this, Observer { list->
            this.favList = list
            mAdapter = MoviesListRecyclerAdapter(context!!, favList!!, TYPE)
            view.movies_recycler_view.adapter = mAdapter

        })

        return view
    }

    override fun onClick(position: Int) {
        var movie = favList?.get(position)
        var detailScreen = Intent(activity, MovieDetailScreen::class.java)
        detailScreen.putExtra("movie", movie)
        startActivity(detailScreen)
    }

    override fun onFavButtonClick(movie: MovieModel.MovieResults) {

    }


}// Required empty public constructor
