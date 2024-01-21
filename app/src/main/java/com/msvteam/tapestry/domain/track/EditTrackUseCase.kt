package com.msvteam.tapestry.domain.track

class EditTrackUseCase(private val trackRepository: ITrackRepository) {
    fun editTrack(vararg track: Track) {
        trackRepository.editTrack(track)
    }
}