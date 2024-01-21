package com.msvteam.tapestry.domain.box

class EditBoxUseCase(private val boxRepository: IBoxRepository) {
    fun editBox(box: Box) {
        boxRepository.editBox(box)
    }
}