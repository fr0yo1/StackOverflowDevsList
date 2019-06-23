package com.stackoverflowdevslist.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stackoverflowdevslist.developersRepository.DeveloperModel


@Database(entities = arrayOf(DeveloperModel::class), version = 1)
abstract class AppDatabase: RoomDatabase() {

}