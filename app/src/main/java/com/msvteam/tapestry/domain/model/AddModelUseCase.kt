package com.msvteam.tapestry.domain.model


class AddModelUseCase(private val modelRepository: IModelRepository) {
    fun addModel(model: Model) {
        modelRepository.addModel(model)
    }
}