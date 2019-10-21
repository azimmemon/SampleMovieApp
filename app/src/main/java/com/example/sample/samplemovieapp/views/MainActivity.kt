package com.example.sample.samplemovieapp.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.example.sample.samplemovieapp.R
import com.example.sample.samplemovieapp.interactor.IMainPresenter
import com.example.sample.samplemovieapp.presenter.MainPresenter
import com.example.sample.samplemovieapp.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        initTabViews()

    }


    override fun onTabReselected(p0: TabLayout.Tab?) {
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        var manager:FragmentManager = supportFragmentManager
        var transaction:FragmentTransaction = manager.beginTransaction()
        var fragment: Fragment? = null

        if (fragment == null){
            fragment = if (p0?.tag!!.equals(Constants.MOVIES_LIST)) MoviesListFragment() else FavouriteMoviesListFragment()
            transaction.replace(R.id.main_container, fragment)
            transaction.commit()
            manager.executePendingTransactions()
        }

    }


    fun initTabViews(){
        main_tabs.addOnTabSelectedListener(this)

        var moviesTab:TabLayout.Tab = main_tabs.newTab()
        var favouriteTab:TabLayout.Tab = main_tabs.newTab()

        moviesTab.text = "Movies"
        moviesTab.tag = Constants.MOVIES_LIST
        favouriteTab.text = "Favourites"
        favouriteTab.tag = Constants.FAVOURITES

        main_tabs.addTab(moviesTab)
        main_tabs.addTab(favouriteTab)

        moviesTab.select()
    }
}
