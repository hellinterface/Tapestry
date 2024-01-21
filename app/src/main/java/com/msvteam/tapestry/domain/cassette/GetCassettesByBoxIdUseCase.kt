package com.msvteam.tapestry.domain.cassette

import androidx.lifecycle.LiveData

class GetCassettesByBoxIdUseCase(private val cassetteRepository: ICassetteRepository) {
    fun getCassettesByBoxId(id: Long): LiveData<List<Cassette>> {
        return cassetteRepository.getCassettesByBoxId(id)
    }
}