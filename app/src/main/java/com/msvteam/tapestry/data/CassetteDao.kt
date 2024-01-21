package com.msvteam.tapestry.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.msvteam.tapestry.domain.cassette.Cassette

@Dao
interface CassetteDao {
    @Insert
    fun insert(vararg cassettes: Cassette): List<Long> // добавить

    @Delete
    fun delete(vararg cassettes: Cassette) // удалить

    @Update
    fun update(vararg cassettes: Cassette) // изменить

    @Query("SELECT * FROM cassettes")
    fun getAll(): LiveData<List<Cassette>> // получить всё

    @Query("SELECT * FROM cassettes WHERE id = :id")
    fun getById(id: Long): Cassette // получить по айди

    @Query("SELECT * FROM cassettes WHERE modelId = :id")
    fun getByModelId(id: Long): List<Cassette> // получить по айди модели

    @Query("SELECT * FROM cassettes WHERE boxId = :id")
    fun getByBoxId(id: Long): List<Cassette> // получить по айди коллекции
}