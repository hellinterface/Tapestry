package com.msvteam.tapestry.domain.cassette;

class DeleteCassetteUseCase(private val cassetteRepository: ICassetteRepository) {
    fun deleteCassette(cassette: Cassette) {
        cassetteRepository.deleteCassette(cassette)
    }
}
