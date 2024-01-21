package com.msvteam.tapestry.domain.tracklist

class EditTracklistUseCase(private val tracklistRepository: ITracklistRepository) {
    fun editArtist(artist: Tracklist) {
        tracklistRepository.editTracklist(artist)
    }
}