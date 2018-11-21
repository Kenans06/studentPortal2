package com.example.gebruiker.studentportal

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

class PortalRepository(private val portalDao: PortalDao) {

    val allPortals: LiveData<List<Portal>> = portalDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(portal: Portal) {
        portalDao.insert(portal)
    }

    @WorkerThread
    suspend fun getUrl(location:Int):String {
         val url: String= portalDao.getUrl(location)
        return url
    }



}