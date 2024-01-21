package com.msvteam.tapestry.domain.cassette

class EditCassetteUseCase(private val cassetteRepository: ICassetteRepository) {
    fun editCassette(cassette: Cassette) {
        cassetteRepository.editCassette(cassette)
    }
}