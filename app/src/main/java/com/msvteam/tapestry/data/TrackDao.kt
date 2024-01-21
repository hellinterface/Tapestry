package com.msvteam.tapestry.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.msvteam.tapestry.domain.track.Track

@Dao
interface TrackDao {
    @Insert
    fun insert(vararg tracks: Track) // добавить

    @Delete
    fun delete(vararg tracks: Track) // удалить

    @Update
    fun update(vararg tracks: Track) // изменить

    @Query("SELECT * FROM tracks")
    fun getAll(): List<Track> // получить всё

    @Query("SELECT * FROM tracks WHERE id = :id")
    fun getById(id: Long): Track // получить по айди

    @Query("SELECT * FROM tracks WHERE tracklistId = :id")
    fun getByTracklistId(id: Long): List<Track> // получить по айди треклиста
}