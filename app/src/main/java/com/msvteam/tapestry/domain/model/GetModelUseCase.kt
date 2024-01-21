package com.msvteam.tapestry.domain.model

import android.util.Log
import androidx.lifecycle.LiveData

class GetModelUseCase(private val modelRepository: IModelRepository) {
    fun getModel(modelId: Long): LiveData<Model> {
        Log.d("SHIZ", "GET MODEL USE CASE")
        val model = modelRepository.getModel(modelId).value
        Log.d("SHIZ", model.toString())
        return modelRepository.getModel(modelId)
    }
}