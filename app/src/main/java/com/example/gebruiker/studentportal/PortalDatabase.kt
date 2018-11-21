package com.example.gebruiker.studentportal

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.launch

@Database(entities = [Portal::class], version = 1,exportSchema = false)
abstract class PortalRoomDatabase : RoomDatabase() {

    abstract fun portalDao(): PortalDao

    companion object {
        @Volatile
        private var INSTANCE: PortalRoomDatabase? = null

        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): PortalRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        PortalRoomDatabase::class.java,
                        "portal_database"
                )
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // Migration is not part of this codelab.
                        .fallbackToDestructiveMigration()
                        .addCallback(PortalDatabaseCallback(scope))
                        .allowMainThreadQueries()
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class PortalDatabaseCallback(
                private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {


                    }
                }
            }
        }

    }

}