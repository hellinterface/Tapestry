package com.msvteam.tapestry.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msvteam.tapestry.data.CassetteRepositoryImpl
import com.msvteam.tapestry.domain.box.Box
import com.msvteam.tapestry.domain.cassette.Cassette
import com.msvteam.tapestry.domain.cassette.DeleteCassetteUseCase
import com.msvteam.tapestry.domain.cassette.GetCassetteListUseCase
import com.msvteam.tapestry.domain.cassette.ICassetteRepository
import com.msvteam.tapestry.domain.model.Model

class HomeViewModel : ViewModel() {

    private val cassetteRepository: ICassetteRepository = CassetteRepositoryImpl

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val getCassetteListUseCase = GetCassetteListUseCase(cassetteRepository)
    private val deleteCassetteUseCase = DeleteCassetteUseCase(cassetteRepository)

    val tapeList = getCassetteListUseCase.getCassetteList()

    fun deleteCassette(cassette: Cassette) {
        deleteCassetteUseCase.deleteCassette(cassette)
    }

}