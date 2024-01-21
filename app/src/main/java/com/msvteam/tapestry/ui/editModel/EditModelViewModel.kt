package com.msvteam.tapestry.ui.editModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msvteam.tapestry.data.CassetteRepositoryImpl
import com.msvteam.tapestry.data.ModelRepositoryImpl
import com.msvteam.tapestry.data.TrackRepositoryImpl
import com.msvteam.tapestry.domain.cassette.AddCassetteUseCase
import com.msvteam.tapestry.domain.cassette.Cassette
import com.msvteam.tapestry.domain.cassette.DeleteCassetteUseCase
import com.msvteam.tapestry.domain.cassette.EditCassetteUseCase
import com.msvteam.tapestry.domain.cassette.GetCassetteUseCase
import com.msvteam.tapestry.domain.cassette.GetCassettesByBoxIdUseCase
import com.msvteam.tapestry.domain.cassette.GetCassettesByModelIdUseCase
import com.msvteam.tapestry.domain.cassette.ICassetteRepository
import com.msvteam.tapestry.domain.model.AddModelUseCase
import com.msvteam.tapestry.domain.model.DeleteModelUseCase
import com.msvteam.tapestry.domain.model.EditModelUseCase
import com.msvteam.tapestry.domain.model.GetModelUseCase
import com.msvteam.tapestry.domain.model.IModelRepository
import com.msvteam.tapestry.domain.model.Model
import com.msvteam.tapestry.domain.track.DeleteTrackUseCase
import com.msvteam.tapestry.domain.track.GetTrackListUseCase

class EditModelViewModel : ViewModel() {

    private val modelRepository: IModelRepository = ModelRepositoryImpl

    var modelObject = MutableLiveData<Model>(Model())

    private val getModelUseCase = GetModelUseCase(modelRepository)
    private val addModelUseCase = AddModelUseCase(modelRepository)
    private val editModelUseCase = EditModelUseCase(modelRepository)
    private val deleteModelUseCase = DeleteModelUseCase(modelRepository)

    private val editCassetteUseCase = EditCassetteUseCase(CassetteRepositoryImpl)
    private val getCassettesByModelIdUseCase = GetCassettesByModelIdUseCase(CassetteRepositoryImpl)

    fun setModel(modelId: Long) {
        modelObject.value = getModelUseCase.getModel(modelId).value
    }

    // Сохранение модели в БД
    fun saveModel() {
        if (modelObject.value!!.id == null) {
            addModelUseCase.addModel(modelObject.value!!)
        }
        else {
            editModelUseCase.editModel(modelObject.value!!)
        }
    }

    // Удаление модели и обновление связанных кассет
    fun deleteModel() {
        if (modelObject.value!!.id != null) {
            val cassettes = getCassettesByModelIdUseCase.getCassettesByModelId(modelObject.value!!.id!!)
            if (cassettes.value != null) {
                for (i in cassettes.value!!) {
                    i.modelId = null
                    editCassetteUseCase.editCassette(i) // записать измененённую кассету
                }
            }
            deleteModelUseCase.deleteModel(modelObject.value!!) // удалить модель из БД
        }
    }

}