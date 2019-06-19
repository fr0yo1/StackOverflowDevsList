package com.stackoverflowdevslist.developersRepository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.stackoverflowdevslist.webServices.RetrofitClientInstance
import com.stackoverflowdevslist.webServices.StackOverflowService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DevelopersRepository {

    private var api: StackOverflowService
    private var developers = MutableLiveData<ArrayList<DeveloperModel>>()

    init {
        val retrofit = RetrofitClientInstance.retrofit
        api = retrofit.create(StackOverflowService::class.java)
    }

    fun getDevelopers(): LiveData<ArrayList<DeveloperModel>> {
        api.getTopDevelopers().enqueue(object : Callback<DevelopersModel> {
            override fun onFailure(call: Call<DevelopersModel>?, t: Throwable?) {
                //TODO
            }

            override fun onResponse(call: Call<DevelopersModel>?, response: Response<DevelopersModel>?) {
                developers.value = response?.body()?.developers
            }
        })

        return developers
    }

    companion object {
        @Volatile private var INSTANCE: DevelopersRepository? = null

        fun getInstance(context: Context): DevelopersRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: DevelopersRepository().also { INSTANCE = it }
            }
    }
}