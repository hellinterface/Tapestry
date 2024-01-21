package com.msvteam.tapestry.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.msvteam.tapestry.domain.model.Model
import com.msvteam.tapestry.domain.tracklist.ITracklistRepository
import com.msvteam.tapestry.domain.tracklist.Tracklist


object TracklistRepositoryImpl: ITracklistRepository {

    private lateinit var db: AppDatabase;
    private lateinit var tracklistDao: TracklistDao;

    fun init(application: Application) {
        db = AppDatabase.getDatabase(application.applicationContext)
        tracklistDao = db.tracklistDao()
    }

    override fun addTracklist(tracklist: Tracklist): Long {
        return tracklistDao.insert(tracklist)
    }

    override fun deleteTracklist(tracklist: Tracklist) {
        tracklistDao.delete(tracklist)
    }

    override fun editTracklist(tracklist: Tracklist) {
        tracklistDao.update(tracklist)
    }

    override fun getTracklist(tracklistId: Long): LiveData<Tracklist> {
        return MutableLiveData<Tracklist>().apply { value = tracklistDao.getById(tracklistId) }
    }

    override fun getTracklistList(): LiveData<List<Tracklist>> {
        return tracklistDao.getAll()
    }

    override fun getTracklistsOfCassette(cassetteId: Long): LiveData<List<Tracklist>> {
        return MutableLiveData<List<Tracklist>>().apply { value = tracklistDao.getByCassetteId(cassetteId) }
    }
}