package com.msvteam.tapestry.domain.track

import androidx.lifecycle.LiveData

class GetTracksFromTracklistUseCase(private val trackRepository: ITrackRepository) {
    fun getTracksFromTracklist(tracklistId: Long): LiveData<List<Track>> {
        return trackRepository.getTracksFromTracklist(tracklistId)
    }
}