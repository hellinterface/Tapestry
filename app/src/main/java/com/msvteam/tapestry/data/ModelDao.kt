package com.msvteam.tapestry.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.msvteam.tapestry.domain.model.Model

@Dao
interface ModelDao {
    @Insert
    fun insert(vararg models: Model) // добавить

    @Delete
    fun delete(vararg models: Model) // удалить

    @Update
    fun update(vararg models: Model) // изменить

    @Query("SELECT * FROM models")
    fun getAll(): LiveData<List<Model>> // получить всё

    @Query("SELECT * FROM models WHERE id=:id")
    fun getById(id: Long): Model // получить по айди
}