package com.example.gebruiker.studentportal

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
@Entity(tableName = "portal_table")
data class Portal(@PrimaryKey(autoGenerate=true)
                  @ColumnInfo(name = "portalId") val portalId:Int,
                  @ColumnInfo(name = "title") val title:String,
                  @ColumnInfo(name="url") val url:String)





