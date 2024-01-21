package com.msvteam.tapestry.domain.box

import androidx.lifecycle.LiveData

interface IBoxRepository {
    fun addBox(box: Box) // добавить
    fun deleteBox(box: Box) // удалить
    fun editBox(box: Box) // изменить
    fun getBox(boxId: Long): LiveData<Box> // получить по айди
    fun getBoxList(): LiveData<List<Box>> // получить всё
}