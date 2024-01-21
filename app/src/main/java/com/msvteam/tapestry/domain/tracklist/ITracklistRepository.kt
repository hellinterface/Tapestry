package com.msvteam.tapestry.domain.tracklist

import androidx.lifecycle.LiveData

interface ITracklistRepository {
    fun addTracklist(tracklist: Tracklist): Long // добавить
    fun deleteTracklist(tracklist: Tracklist) // удалить
    fun editTracklist(tracklist: Tracklist) // изменить
    fun getTracklist(tracklistId: Long): LiveData<Tracklist> // получить по айди
    fun getTracklistList(): LiveData<List<Tracklist>> // получить всё
    fun getTracklistsOfCassette(cassetteId: Long): LiveData<List<Tracklist>> // получить по айди кассеты
}