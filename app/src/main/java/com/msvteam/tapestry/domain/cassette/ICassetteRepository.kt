package com.msvteam.tapestry.domain.cassette

import androidx.lifecycle.LiveData

interface ICassetteRepository {
    fun addCassette(cassette: Cassette): List<Long> // добавить
    fun deleteCassette(cassette: Cassette) // удалить
    fun editCassette(cassette: Cassette) // изменить
    fun getCassette(cassetteId: Long): Cassette // получить по айди
    fun getCassetteList(): LiveData<List<Cassette>> // получить всё
    fun getCassettesByBoxId(boxId: Long): LiveData<List<Cassette>> // получить с айди коллекции
    fun getCassettesByModelId(modelId: Long): LiveData<List<Cassette>> // получить с айди модели
}