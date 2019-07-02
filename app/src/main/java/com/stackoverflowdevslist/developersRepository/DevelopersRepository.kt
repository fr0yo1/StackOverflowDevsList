package com.stackoverflowdevslist.developersRepository

import android.content.Context
import androidx.lifecycle.LiveData
import com.stackoverflowdevslist.persistance.AppDatabase
import com.stackoverflowdevslist.persistance.DeveloperDao
import com.stackoverflowdevslist.webServices.RetrofitClientInstance
import com.stackoverflowdevslist.webServices.StackOverflowService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DevelopersRepository(context: Context) {

    var developers : LiveData<List<DeveloperModel>>

    private val developersDAO: DeveloperDao = AppDatabase.getInstance(context).developerDao()
    private val webAPI = RetrofitClientInstance.retrofit.create(StackOverflowService::class.java)

    init {
        getDevelopersFromServer()
        developers = developersDAO.getAll()
    }

    private fun getDevelopersFromServer() {
        webAPI.getTopDevelopers().enqueue(object : Callback<DevelopersModel> {
            override fun onFailure(call: Call<DevelopersModel>?, t: Throwable?) {
                //TODO
            }

            override fun onResponse(call: Call<DevelopersModel>?, response: Response<DevelopersModel>?) {
                response?.body()?.developers?.let {
                    GlobalScope.launch(Dispatchers.IO) {
                        developersDAO.insertAll(it)
                    }
                }
            }
        })
    }

    fun getDeveloperWith(id: String): LiveData<DeveloperModel> {
        return developersDAO.getDeveloperWith(id)
    }

    companion object {
        @Volatile private var INSTANCE: DevelopersRepository? = null

        fun getInstance(context: Context): DevelopersRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: DevelopersRepository(context).also {
                    INSTANCE = it
                }
            }
    }
}