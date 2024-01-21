package com.msvteam.tapestry.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.msvteam.tapestry.domain.track.ITrackRepository
import com.msvteam.tapestry.domain.track.Track


object TrackRepositoryImpl: ITrackRepository {

    private lateinit var db: AppDatabase;
    private lateinit var trackDao: TrackDao;

    fun init(application: Application) {
        db = AppDatabase.getDatabase(application.applicationContext)
        trackDao = db.trackDao()
    }

    override fun addTrack(track: Array<out Track>) {
        trackDao.insert(*track)
    }

    override fun deleteTrack(track: Array<out Track>) {
        trackDao.delete(*track)
    }

    override fun editTrack(track: Array<out Track>) {
        trackDao.update(*track)
    }

    override fun getTrack(trackId: Long): LiveData<Track> {
        return MutableLiveData<Track>().apply { value = trackDao.getById(trackId) }
    }

    override fun getTrackList(): LiveData<List<Track>> {
        return MutableLiveData<List<Track>>().apply { value = trackDao.getAll() }
    }

    override fun getTracksFromTracklist(tracklistId: Long): LiveData<List<Track>> {
        return MutableLiveData<List<Track>>().apply { value = trackDao.getByTracklistId(tracklistId) }
    }
}