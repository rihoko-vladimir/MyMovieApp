package com.rihoko.movieapp.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rihoko.movieapp.MainApplication

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract val movieCacheDao: MovieDao

    companion object {
        private const val cacheDbName = "cache"
        val databaseInstance: MoviesDatabase by lazy {
            Room.databaseBuilder(
                MainApplication.appContext,
                MoviesDatabase::class.java,
                cacheDbName
            ).fallbackToDestructiveMigration().build()
        }
    }
}