package com.msvteam.tapestry.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.msvteam.tapestry.domain.track.Track
import com.msvteam.tapestry.domain.tracklist.Tracklist

@Dao
interface TracklistDao {
    @Insert
    fun insert(tracklist: Tracklist): Long // добавить

    @Delete
    fun delete(vararg tracklists: Tracklist) // удалить

    @Update
    fun update(vararg tracklists: Tracklist) // изменить

    @Query("SELECT * FROM tracklists")
    fun getAll(): LiveData<List<Tracklist>> // получить всё

    @Query("SELECT * FROM tracklists WHERE id = :id")
    fun getById(id: Long): Tracklist // получить по айди

    @Query("SELECT * FROM tracklists WHERE cassetteId = :id")
    fun getByCassetteId(id: Long): List<Tracklist> // получить по айди кассеты
}