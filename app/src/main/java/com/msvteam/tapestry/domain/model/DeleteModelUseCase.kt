package com.msvteam.tapestry.domain.model

class DeleteModelUseCase(private val modelRepository: IModelRepository) {
    fun deleteModel(model: Model) {
        modelRepository.deleteModel(model)
    }
}