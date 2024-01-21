package com.msvteam.tapestry.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.msvteam.tapestry.domain.box.Box
import com.msvteam.tapestry.domain.cassette.Cassette
import com.msvteam.tapestry.domain.model.Model
import com.msvteam.tapestry.domain.track.Track
import com.msvteam.tapestry.domain.tracklist.Tracklist


@Database(entities = [Cassette::class, Model::class, Tracklist::class, Track::class, Box::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cassetteDao(): CassetteDao
    abstract fun modelDao(): ModelDao
    abstract fun trackDao(): TrackDao
    abstract fun tracklistDao(): TracklistDao
    abstract fun boxDao(): BoxDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "app_database").allowMainThreadQueries().build()
                }
            }
            return INSTANCE!!
        }
    }
}