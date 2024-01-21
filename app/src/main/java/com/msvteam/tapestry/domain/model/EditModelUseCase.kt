package com.msvteam.tapestry.domain.model

class EditModelUseCase(private val modelRepository: IModelRepository) {
    fun editModel(model: Model) {
        modelRepository.editModel(model)
    }
}