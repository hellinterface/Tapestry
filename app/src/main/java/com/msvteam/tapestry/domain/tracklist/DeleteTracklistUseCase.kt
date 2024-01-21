package com.msvteam.tapestry.domain.tracklist

class DeleteTracklistUseCase(private val tracklistRepository: ITracklistRepository) {
    fun deleteTracklist(tracklist: Tracklist) {
        tracklistRepository.deleteTracklist(tracklist)
    }
}