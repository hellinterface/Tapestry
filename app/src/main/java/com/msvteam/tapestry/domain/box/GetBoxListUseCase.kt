package com.msvteam.tapestry.domain.box

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class GetBoxListUseCase(private val boxRepository: IBoxRepository) {
    fun getBoxList(): LiveData<List<Box>> {
        return boxRepository.getBoxList()
    }
}