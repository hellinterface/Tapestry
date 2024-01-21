package com.msvteam.tapestry.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.msvteam.tapestry.domain.cassette.Cassette
import com.msvteam.tapestry.domain.cassette.ICassetteRepository


object CassetteRepositoryImpl: ICassetteRepository {

    private lateinit var db: AppDatabase;
    private lateinit var cassetteDao: CassetteDao;

    fun init(application: Application) {
        db = AppDatabase.getDatabase(application.applicationContext)
        cassetteDao = db.cassetteDao()
    }

    override fun addCassette(cassette: Cassette): List<Long> {
        return cassetteDao.insert(cassette)
    }

    override fun deleteCassette(cassette: Cassette) {
        cassetteDao.delete(cassette)
    }

    override fun editCassette(cassette: Cassette) {
        cassetteDao.update(cassette)
    }

    override fun getCassette(cassetteId: Long): Cassette {
        //return MutableLiveData<Cassette>().apply { value = cassetteDao.getById(cassetteId) }
        return cassetteDao.getById(cassetteId)
    }

    override fun getCassetteList(): LiveData<List<Cassette>> {
        return cassetteDao.getAll()
    }

    override fun getCassettesByBoxId(id: Long): LiveData<List<Cassette>> {
        return MutableLiveData<List<Cassette>>().apply { value = cassetteDao.getByBoxId(id) }
    }

    override fun getCassettesByModelId(id: Long): LiveData<List<Cassette>> {
        return MutableLiveData<List<Cassette>>().apply { value = cassetteDao.getByModelId(id) }
    }
}