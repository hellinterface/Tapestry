package com.msvteam.tapestry.domain.tracklist

import androidx.lifecycle.LiveData

class GetTracklistListUseCase(private val tracklistRepository: ITracklistRepository) {
    fun getTracklistList(): LiveData<List<Tracklist>> {
        return tracklistRepository.getTracklistList()
    }
}