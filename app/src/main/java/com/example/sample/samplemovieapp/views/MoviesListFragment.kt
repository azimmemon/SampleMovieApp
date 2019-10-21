package com.example.sample.samplemovieapp.views

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast

import com.example.sample.samplemovieapp.R
import com.example.sample.samplemovieapp.adapter.MoviesListRecyclerAdapter
import com.example.sample.samplemovieapp.data.MovieModel
import com.example.sample.samplemovieapp.interactor.IMainView
import com.example.sample.samplemovieapp.presenter.MainPresenter
import kotlinx.android.synthetic.main.fragment_movies_list.view.*
import android.support.v7.widget.SearchView


/**
 * A simple [Fragment] subclass.
 */
class MoviesListFragment : Fragment(), IMainView, MoviesListRecyclerAdapter.OnMovieTitleClickListener {

    lateinit var mPresenter: MainPresenter
    lateinit var mAdapter:MoviesListRecyclerAdapter
    lateinit var recyclerView: RecyclerView
    var movieList: MutableList<MovieModel.MovieResults>? = null
    var TYPE  = "MOVIES"
    private var currentPage = 1
    private var isLastPage = false
    private var totalPage = 10
    private var isLoading = false
    val PAGE_START = 1
    var itemCount = 0



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        initViews(view)

        mPresenter = MainPresenter()
        mPresenter.setView(this)

        if (movieList == null)
        mPresenter.requestMoviesData(currentPage.toString())

        setHasOptionsMenu(true)

        return view
    }


    fun initViews(view: View) {
        var layoutManager = GridLayoutManager(activity, 2)
        view.movies_recycler_view.layoutManager = layoutManager
        recyclerView = view.movies_recycler_view


    }


    override fun onDataFetchSuccess(data:MovieModel) {
        this.movieList = data.results
        mAdapter = MoviesListRecyclerAdapter(context!!, data.results, TYPE)
        mAdapter.setOnTileClickListener(this)
        recyclerView.adapter = mAdapter



    }



    override fun onDataFetchFailure(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()

    }

    override fun onClick(position: Int) {
        var movie = movieList?.get(position)
        var detailScreen = Intent(activity, MovieDetailScreen::class.java)
        detailScreen.putExtra("movie", movie)
        startActivity(detailScreen)

    }

    override fun onFavButtonClick(movie: MovieModel.MovieResults) {
        Toast.makeText(activity, "Item added to favourite", Toast.LENGTH_LONG).show()
        mPresenter.addFavouriteMovie(movie)
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_main, menu)

        val mSearch = menu?.findItem(R.id.action_search)

        val mSearchView = mSearch?.getActionView() as SearchView

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(p0: String): Boolean {
                mAdapter.filter(p0)
                return true
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }
        })
    }

}