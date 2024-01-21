package com.msvteam.tapestry.domain.cassette

import androidx.lifecycle.LiveData

class GetCassetteListUseCase(private val cassetteRepository: ICassetteRepository) {
    fun getCassetteList(): LiveData<List<Cassette>> {
        return cassetteRepository.getCassetteList()
    }
}