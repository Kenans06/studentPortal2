package com.example.gebruiker.studentportal

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface PortalDao {

    @Query("SELECT * FROM portal_table")
    fun getAll(): LiveData<List<Portal>>

    @Query("SELECT url FROM portal_table where portalId LIKE :id")
    fun getUrl(id:Int): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(title: Portal)

    @Query("DELETE from portal_table")
    fun deleteAll()
}