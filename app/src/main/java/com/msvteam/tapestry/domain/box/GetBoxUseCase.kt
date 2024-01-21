package com.msvteam.tapestry.domain.box

import androidx.lifecycle.LiveData

class GetBoxUseCase(private val boxRepository: IBoxRepository) {
    fun getBox(boxId: Long): LiveData<Box> {
        return boxRepository.getBox(boxId)
    }
}