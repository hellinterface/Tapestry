package com.msvteam.tapestry.domain.tracklist

import androidx.lifecycle.LiveData

class GetTracklistsOfCassetteUseCase(private val tracklistRepository: ITracklistRepository) {
    fun getTracklistsOfCassette(cassetteId: Long): LiveData<List<Tracklist>> {
        return tracklistRepository.getTracklistsOfCassette(cassetteId)
    }
}