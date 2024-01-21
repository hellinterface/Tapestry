package com.msvteam.tapestry.ui.editCassette

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msvteam.tapestry.data.BoxRepositoryImpl
import com.msvteam.tapestry.data.CassetteRepositoryImpl
import com.msvteam.tapestry.data.ModelRepositoryImpl
import com.msvteam.tapestry.data.TrackRepositoryImpl
import com.msvteam.tapestry.data.TracklistRepositoryImpl
import com.msvteam.tapestry.domain.box.Box
import com.msvteam.tapestry.domain.box.GetBoxUseCase
import com.msvteam.tapestry.domain.cassette.AddCassetteUseCase
import com.msvteam.tapestry.domain.cassette.Cassette
import com.msvteam.tapestry.domain.cassette.DeleteCassetteUseCase
import com.msvteam.tapestry.domain.cassette.EditCassetteUseCase
import com.msvteam.tapestry.domain.cassette.GetCassetteUseCase
import com.msvteam.tapestry.domain.cassette.ICassetteRepository
import com.msvteam.tapestry.domain.model.GetModelUseCase
import com.msvteam.tapestry.domain.model.Model
import com.msvteam.tapestry.domain.track.AddTrackUseCase
import com.msvteam.tapestry.domain.track.DeleteTrackUseCase
import com.msvteam.tapestry.domain.track.EditTrackUseCase
import com.msvteam.tapestry.domain.track.GetTrackListUseCase
import com.msvteam.tapestry.domain.track.GetTracksFromTracklistUseCase
import com.msvteam.tapestry.domain.track.Track
import com.msvteam.tapestry.domain.tracklist.AddTracklistUseCase
import com.msvteam.tapestry.domain.tracklist.DeleteTracklistUseCase
import com.msvteam.tapestry.domain.tracklist.GetTracklistsOfCassetteUseCase
import com.msvteam.tapestry.domain.tracklist.Tracklist

class EditCassetteViewModel : ViewModel() {

    // Объекты кассеты, её модели и коллекции
    var cassetteObject = MutableLiveData<Cassette>(Cassette())
    var modelObject = MutableLiveData<Model>(Model("Модель не выбрана"))
    var boxObject = MutableLiveData<Box>(Box("Коллекция не выбрана"))

    // use cases кассета
    private val cassetteRepository: ICassetteRepository = CassetteRepositoryImpl
    private val getCassetteUseCase = GetCassetteUseCase(cassetteRepository)
    private val addCassetteUseCase = AddCassetteUseCase(cassetteRepository)
    private val editCassetteUseCase = EditCassetteUseCase(cassetteRepository)
    private val deleteCassetteUseCase = DeleteCassetteUseCase(cassetteRepository)

    // use cases песня
    private val trackRepository = TrackRepositoryImpl
    private val getTrackListUseCase = GetTrackListUseCase(trackRepository)
    private val deleteTrackUseCase = DeleteTrackUseCase(trackRepository)
    private val editTrackUseCase = EditTrackUseCase(trackRepository)
    private val addTrackUseCase = AddTrackUseCase(trackRepository)
    private val getTracksFromTracklistUseCase = GetTracksFromTracklistUseCase(trackRepository);

    // use cases список песен
    private val tracklistRepository = TracklistRepositoryImpl
    private val getTracklistsOfCassetteUseCase = GetTracklistsOfCassetteUseCase(tracklistRepository)
    private val addTracklistUseCase = AddTracklistUseCase(tracklistRepository)
    private val deleteTracklistUseCase = DeleteTracklistUseCase(tracklistRepository)

    // use cases коробка
    private val getBoxUseCase = GetBoxUseCase(BoxRepositoryImpl)

    // use cases модель
    private val getModelUseCase = GetModelUseCase(ModelRepositoryImpl)

    // id треклистов
    var tracklistId_sideA = MutableLiveData<Long>().apply {
        value = null
    }
    var tracklistId_sideB = MutableLiveData<Long>().apply {
        value = null
    }

    // списки песен
    var trackList_sideA = MutableLiveData<MutableList<Track>>().apply {
        value = mutableListOf<Track>()
    }
    var trackList_sideB = MutableLiveData<MutableList<Track>>().apply {
        value = mutableListOf<Track>()
    }

    // задать кассету с определённым айди
    fun setCassette(cassetteId: Long) {
        val result = getCassetteUseCase.getCassette(cassetteId)
        Log.d("SET_CASSETTE", result.toString())
        cassetteObject.value = result
        if (result.modelId != null) setModel(result.modelId!!)
        if (result.boxId != null) setBox(result.boxId!!)
        //refreshTracklists()
    }

    // задать модель
    fun setModel(modelId: Long) {
        val model = getModelUseCase.getModel(modelId)
        cassetteObject.value!!.modelId = model.value!!.id
        modelObject.value = model.value
    }

    // задать коллекцию
    fun setBox(boxId: Long) {
        Log.d("SET_BOX", boxId.toString())
        val zero: Long = 0
        if (boxId == null || boxId == zero) {
            cassetteObject.value!!.boxId = null
            boxObject.value = Box("Коллекция не выбрана")
        }
        else {
            val box = getBoxUseCase.getBox(boxId)
            cassetteObject.value!!.boxId = box.value!!.id
            boxObject.value = box.value
        }
    }

    // обновить списки песен (айди и сами списки)
    fun refreshTracklists() {
        if (cassetteObject.value!!.id != Cassette.UNDEFINED_ID) {
            var tracklists = getTracklistsOfCassetteUseCase.getTracklistsOfCassette(cassetteObject.value!!.id!!)
            Log.d("TRACKLISTS", tracklists.value.toString())
            if (tracklists.value == null || tracklists.value!!.isEmpty()) return
            tracklistId_sideA.value = tracklists.value!![0].id
            tracklistId_sideB.value = tracklists.value!![1].id
            var trackListA = getTracksFromTracklistUseCase.getTracksFromTracklist(tracklists.value!![0].id!!)
            var trackListB = getTracksFromTracklistUseCase.getTracksFromTracklist(tracklists.value!![1].id!!)
            trackList_sideA.value = trackListA.value!!.toMutableList()
            trackList_sideB.value = trackListB.value!!.toMutableList()
        }
    }

    // сохранить
    fun saveCassette() {
        // Кассета
        if (cassetteObject.value!!.id == null) {
            cassetteObject.value!!.id = addCassetteUseCase.addCassette(cassetteObject.value!!)
        }
        else {
            editCassetteUseCase.editCassette(cassetteObject.value!!)
        }
        Log.d("SAVIJG", cassetteObject.value!!.toString())
        Log.d("SAVIJG", cassetteObject.value!!.id.toString())
        // Списки песен
        if (tracklistId_sideA.value == null) {
            val tracklist = Tracklist(cassetteObject.value!!.id!!)
            val result = addTracklistUseCase.addTracklist(tracklist)
            tracklistId_sideA.value = result
        }
        if (tracklistId_sideB.value == null) {
            val tracklist = Tracklist(cassetteObject.value!!.id!!)
            val result = addTracklistUseCase.addTracklist(tracklist)
            tracklistId_sideB.value = result
        }
        Log.d("IDSIDEA", tracklistId_sideA.value.toString())
        Log.d("IDSIDEB", tracklistId_sideB.value.toString())
        var tracklists = getTracklistsOfCassetteUseCase.getTracklistsOfCassette(cassetteObject.value!!.id!!)
        Log.d("TRACKLISTS", tracklists.value.toString())
        if (tracklists.value == null || tracklists.value!!.isEmpty()) return
        tracklistId_sideA.value = tracklists.value!![0].id
        tracklistId_sideB.value = tracklists.value!![1].id
        // Песни
        val tracklistExisting = mutableListOf<Track>() // существующие
        val tracklistNew = mutableListOf<Track>() // новые
        for (i in trackList_sideA.value!!) {
            Log.d("SIDEA", i.artist + " " + i.title + " " + i.id)
            i.tracklistId = tracklistId_sideA.value!!
            if (i.id != null) tracklistExisting.add(i) else tracklistNew.add(i)
        }
        for (i in trackList_sideB.value!!) {
            Log.d("SIDEB", i.artist + " " + i.title + " " + i.id)
            i.tracklistId = tracklistId_sideB.value!!
            if (i.id != null) tracklistExisting.add(i) else tracklistNew.add(i)
        }
        editTrackUseCase.editTrack(*tracklistExisting.toTypedArray()) // изменить существующие песни
        addTrackUseCase.addTrack(*tracklistNew.toTypedArray()) // добавить новые песни
    }

    fun deleteCassette() {
        if (cassetteObject.value!!.id != null) {
            val tracklists = getTracklistsOfCassetteUseCase.getTracklistsOfCassette(cassetteObject.value!!.id!!)
            if (tracklists.value != null) {
                for (a in tracklists.value!!) {
                    val tracks = getTracksFromTracklistUseCase.getTracksFromTracklist(a.id!!)
                    if (tracks.value != null) {
                        for (b in tracks.value!!) {
                            deleteTrackUseCase.deleteTrack(b)
                        }
                    }
                    deleteTracklistUseCase.deleteTracklist(a)
                }
            }
            deleteCassetteUseCase.deleteCassette(cassetteObject.value!!)
        }
    }

}