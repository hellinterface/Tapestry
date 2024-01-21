package com.msvteam.tapestry.domain.tracklist

import androidx.lifecycle.LiveData

class GetTracklistUseCase(private val tracklistRepository: ITracklistRepository) {
    fun getTracklist(tracklistId: Long): LiveData<Tracklist> {
        return tracklistRepository.getTracklist(tracklistId)
    }
}