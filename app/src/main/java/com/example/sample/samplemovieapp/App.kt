package com.example.sample.samplemovieapp

import android.app.Application
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.example.sample.samplemovieapp.repository.MovieDatabase

/**
 * Created by azimmemon on 18/10/19.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this
    }

    companion object {

        lateinit var instance: App


        fun <T : RoomDatabase> getDatabase(cls: Class<T>, dbName: String): T {
            lateinit var obj: T
            synchronized(MovieDatabase::class.java) {
                obj = Room.databaseBuilder(instance, cls, dbName).build()
            }
            return obj
        }

    }





}