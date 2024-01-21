package com.msvteam.tapestry.domain.cassette

class AddCassetteUseCase(private val cassetteRepository: ICassetteRepository) {
    fun addCassette(cassette: Cassette) : Long? {
        val result = cassetteRepository.addCassette(cassette)
        if (result.isNotEmpty()) return result[0]
        return null
    }
}