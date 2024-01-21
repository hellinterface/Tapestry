package com.msvteam.tapestry.domain.track

import androidx.lifecycle.LiveData

interface ITrackRepository {
    fun addTrack(track: Array<out Track>) // добавить
    fun deleteTrack(track: Array<out Track>) // удалить
    fun editTrack(track: Array<out Track>) // изменить
    fun getTrack(trackId: Long): LiveData<Track> // получить по айди
    fun getTrackList(): LiveData<List<Track>> // получить всё
    fun getTracksFromTracklist(tracklistId: Long): LiveData<List<Track>> // получить по айди треклиста
}