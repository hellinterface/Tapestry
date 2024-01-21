package com.msvteam.tapestry.domain.box


class AddBoxUseCase(private val boxRepository: IBoxRepository) {
    fun addBox(box: Box) {
        boxRepository.addBox(box)
    }
}