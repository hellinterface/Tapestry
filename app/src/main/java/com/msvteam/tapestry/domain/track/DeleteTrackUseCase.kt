package com.msvteam.tapestry.domain.track

class DeleteTrackUseCase(private val trackRepository: ITrackRepository) {
    fun deleteTrack(vararg track: Track) {
        trackRepository.deleteTrack(track)
    }
}