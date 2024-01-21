package com.msvteam.tapestry.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.msvteam.tapestry.domain.box.Box
import com.msvteam.tapestry.domain.box.IBoxRepository
import com.msvteam.tapestry.domain.tracklist.Tracklist


object BoxRepositoryImpl: IBoxRepository {

    private lateinit var db: AppDatabase;
    private lateinit var boxDao: BoxDao;

    fun init(application: Application) {
        db = AppDatabase.getDatabase(application.applicationContext)
        boxDao = db.boxDao()
    }

    override fun addBox(box: Box) {
        boxDao.insert(box)
    }

    override fun deleteBox(box: Box) {
        boxDao.delete(box)
    }

    override fun editBox(box: Box) {
        boxDao.update(box)
    }

    override fun getBox(boxId: Long): LiveData<Box> {
        return MutableLiveData<Box>().apply { value = boxDao.getById(boxId) }
    }

    override fun getBoxList(): LiveData<List<Box>> {
        return boxDao.getAll()
    }
}