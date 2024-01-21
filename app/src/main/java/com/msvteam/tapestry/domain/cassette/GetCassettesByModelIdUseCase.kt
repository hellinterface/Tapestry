package com.msvteam.tapestry.domain.cassette

import androidx.lifecycle.LiveData

class GetCassettesByModelIdUseCase(private val cassetteRepository: ICassetteRepository) {
    fun getCassettesByModelId(id: Long): LiveData<List<Cassette>> {
        return cassetteRepository.getCassettesByModelId(id)
    }
}