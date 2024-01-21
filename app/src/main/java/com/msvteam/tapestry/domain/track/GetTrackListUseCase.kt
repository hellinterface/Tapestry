package com.msvteam.tapestry.domain.track

import androidx.lifecycle.LiveData

class GetTrackListUseCase(private val trackRepository: ITrackRepository) {
    fun getTrackList(): LiveData<List<Track>> {
        return trackRepository.getTrackList()
    }
}