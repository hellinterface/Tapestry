package com.msvteam.tapestry.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.msvteam.tapestry.domain.cassette.Cassette
import com.msvteam.tapestry.domain.model.IModelRepository
import com.msvteam.tapestry.domain.model.Model

object ModelRepositoryImpl : IModelRepository {

    private lateinit var db: AppDatabase;
    private lateinit var modelDao: ModelDao;

    fun init(application: Application) {
        db = AppDatabase.getDatabase(application.applicationContext)
        modelDao = db.modelDao()
    }

    override fun addModel(model: Model) {
        modelDao.insert(model)
    }

    override fun deleteModel(model: Model) {
        modelDao.delete(model)
    }

    override fun editModel(model: Model) {
        modelDao.update(model)
    }

    override fun getModel(modelId: Long): LiveData<Model> {
        return MutableLiveData<Model>().apply { value = modelDao.getById(modelId) }
    }

    override fun getModelList(): LiveData<List<Model>> {
        val modelList = modelDao.getAll()
        return modelList
    }
}