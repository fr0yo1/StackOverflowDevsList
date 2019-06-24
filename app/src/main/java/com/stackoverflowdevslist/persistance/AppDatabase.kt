package com.stackoverflowdevslist.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stackoverflowdevslist.developersRepository.DeveloperModel

@Database(entities = arrayOf(DeveloperModel::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun developerDao(): DeveloperDao

    companion object {

        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "AppDatabase.db")
                .build()
    }
}