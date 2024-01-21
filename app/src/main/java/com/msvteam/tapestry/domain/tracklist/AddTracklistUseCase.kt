package com.msvteam.tapestry.domain.tracklist



class AddTracklistUseCase(private val tracklistRepository: ITracklistRepository) {
    fun addTracklist(tracklist: Tracklist) : Long? {
        return tracklistRepository.addTracklist(tracklist)
    }
}