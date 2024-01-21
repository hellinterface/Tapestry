package com.msvteam.tapestry.domain.box

class DeleteBoxUseCase(private val boxRepository: IBoxRepository) {
    fun deleteBox(box: Box) {
        boxRepository.deleteBox(box)
    }
}