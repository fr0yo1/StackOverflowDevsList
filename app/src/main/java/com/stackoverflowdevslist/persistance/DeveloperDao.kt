package com.stackoverflowdevslist.persistance

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stackoverflowdevslist.developersRepository.DeveloperModel

@Dao
interface DeveloperDao {
    @Query("SELECT * FROM DeveloperModel WHERE user_id = :id")
    fun getDeveloperWith(id: String): LiveData<DeveloperModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(developers: ArrayList<DeveloperModel>)

    @Query("SELECT * FROM DeveloperModel")
    fun getAll(): LiveData<List<DeveloperModel>>
}