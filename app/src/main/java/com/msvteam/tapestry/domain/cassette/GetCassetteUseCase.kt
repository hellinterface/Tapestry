package com.msvteam.tapestry.domain.cassette

import androidx.lifecycle.LiveData

class GetCassetteUseCase(private val cassetteRepository: ICassetteRepository) {
    fun getCassette(cassetteId: Long): Cassette {
        return cassetteRepository.getCassette(cassetteId)
    }
}