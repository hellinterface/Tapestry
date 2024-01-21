package com.msvteam.tapestry.domain.model

import androidx.lifecycle.LiveData

class GetModelListUseCase(private val modelRepository: IModelRepository) {
    fun getModelList(): LiveData<List<Model>> {
        return modelRepository.getModelList()
    }
}