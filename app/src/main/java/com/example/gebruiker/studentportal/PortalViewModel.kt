package com.example.gebruiker.studentportal

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.*
import kotlinx.coroutines.experimental.android.Main
import kotlin.coroutines.experimental.CoroutineContext

class PortalViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: PortalRepository
    val allPortals: LiveData<List<Portal>>


    init {
        val portalDao = PortalRoomDatabase.getDatabase(application, scope).portalDao()
        repository = PortalRepository(portalDao)
        allPortals = repository.allPortals
    }

    suspend fun getUrl(location: Int): String {
        return repository.getUrl(location)
    }

    fun insert(portal: Portal) = scope.launch(Dispatchers.IO) {
        repository.insert(portal)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}