package com.msvteam.tapestry.ui.editBox

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msvteam.tapestry.data.BoxRepositoryImpl
import com.msvteam.tapestry.data.CassetteRepositoryImpl
import com.msvteam.tapestry.data.ModelRepositoryImpl
import com.msvteam.tapestry.domain.box.AddBoxUseCase
import com.msvteam.tapestry.domain.box.Box
import com.msvteam.tapestry.domain.box.DeleteBoxUseCase
import com.msvteam.tapestry.domain.box.EditBoxUseCase
import com.msvteam.tapestry.domain.box.GetBoxUseCase
import com.msvteam.tapestry.domain.cassette.EditCassetteUseCase
import com.msvteam.tapestry.domain.cassette.GetCassettesByBoxIdUseCase
import com.msvteam.tapestry.domain.model.AddModelUseCase
import com.msvteam.tapestry.domain.model.DeleteModelUseCase
import com.msvteam.tapestry.domain.model.EditModelUseCase
import com.msvteam.tapestry.domain.model.GetModelUseCase
import com.msvteam.tapestry.domain.model.IModelRepository
import com.msvteam.tapestry.domain.model.Model

class EditBoxViewModel : ViewModel() {

    private val boxRepository = BoxRepositoryImpl
    private val addBoxUseCase = AddBoxUseCase(boxRepository)
    private val deleteBoxUseCase = DeleteBoxUseCase(boxRepository)
    private val editBoxUseCase = EditBoxUseCase(boxRepository)
    private val getBoxUseCase = GetBoxUseCase(boxRepository)

    private val editCassetteUseCase = EditCassetteUseCase(CassetteRepositoryImpl)
    private val getCassettesByBoxIdUseCase = GetCassettesByBoxIdUseCase(CassetteRepositoryImpl)

    var boxObject = MutableLiveData<Box>(Box())

    // задать кассету с определённым айди
    fun setBox(boxId: Long) {
        val result = getBoxUseCase.getBox(boxId)
        boxObject.value = result.value
    }

    fun saveBox() {
        if (boxObject.value!!.id == null) {
            addBoxUseCase.addBox(boxObject.value!!)
        }
        else {
            editBoxUseCase.editBox(boxObject.value!!)
        }
    }

    fun deleteBox() {
        if (boxObject.value!!.id != null) {
            val cassettes = getCassettesByBoxIdUseCase.getCassettesByBoxId(boxObject.value!!.id!!)
            if (cassettes.value != null) {
                for (i in cassettes.value!!) {
                    i.boxId = null
                    editCassetteUseCase.editCassette(i)
                }
            }
            deleteBoxUseCase.deleteBox(boxObject.value!!)
        }
    }

}