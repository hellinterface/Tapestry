package com.msvteam.tapestry.domain.track

import androidx.lifecycle.LiveData

class GetTrackUseCase(private val trackRepository: ITrackRepository) {
    fun getTrack(trackId: Long): LiveData<Track> {
        return trackRepository.getTrack(trackId)
    }
}