package com.msvteam.tapestry.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.msvteam.tapestry.domain.box.Box

@Dao
interface BoxDao {
    @Insert
    fun insert(vararg boxes: Box) // добавить

    @Delete
    fun delete(vararg boxes: Box) // удалить

    @Update
    fun update(vararg boxes: Box) // изменить

    @Query("SELECT * FROM boxes")
    fun getAll(): LiveData<List<Box>> // получить всё

    @Query("SELECT * FROM boxes WHERE id = :id")
    fun getById(id: Long): Box // получить по айди
}