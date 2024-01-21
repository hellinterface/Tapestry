package com.msvteam.tapestry.ui.boxList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msvteam.tapestry.data.BoxRepositoryImpl
import com.msvteam.tapestry.domain.model.GetModelListUseCase
import com.msvteam.tapestry.data.ModelRepositoryImpl
import com.msvteam.tapestry.domain.box.Box
import com.msvteam.tapestry.domain.box.GetBoxListUseCase

class BoxListViewModel : ViewModel() {
    private val getBoxListUseCase = GetBoxListUseCase(BoxRepositoryImpl)

    val boxList = getBoxListUseCase.getBoxList()
}