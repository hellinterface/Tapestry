package com.msvteam.tapestry.domain.model

import androidx.lifecycle.LiveData

interface IModelRepository {
    fun addModel(model: Model) // добавить
    fun deleteModel(model: Model) // удалить
    fun editModel(model: Model) // изменить
    fun getModel(modelId: Long): LiveData<Model> // получить по айди
    fun getModelList(): LiveData<List<Model>> // получить всё
}