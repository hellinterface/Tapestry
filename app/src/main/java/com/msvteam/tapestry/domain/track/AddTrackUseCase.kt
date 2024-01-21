package com.msvteam.tapestry.domain.track


class AddTrackUseCase(private val trackRepository: ITrackRepository) {
    fun addTrack(vararg track: Track) {
        trackRepository.addTrack(track)
    }
}