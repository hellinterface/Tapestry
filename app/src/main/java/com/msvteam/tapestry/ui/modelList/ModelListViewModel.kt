package com.msvteam.tapestry.ui.modelList

import androidx.lifecycle.ViewModel
import com.msvteam.tapestry.domain.model.GetModelListUseCase
import com.msvteam.tapestry.data.ModelRepositoryImpl

class ModelListViewModel : ViewModel() {
    private val modelRepository = ModelRepositoryImpl
    private val getModelListUseCase = GetModelListUseCase(modelRepository)

    val modelList = getModelListUseCase.getModelList()
}