package com.stackoverflowdevslist.mainActivity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stackoverflowdevslist.R
import com.stackoverflowdevslist.webServices.RetrofitClientInstance
import com.stackoverflowdevslist.webServices.StackOverflowService
import com.stackoverflowdevslist.webServices.responseModels.DevelopersModelResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class DevList : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dev_list, container, false)
    }

    override fun onStart() {
        super.onStart()

        val retrofit = RetrofitClientInstance.retrofit
        val api = retrofit.create(StackOverflowService::class.java)

        api.getTopDevelopers().enqueue(object : Callback<DevelopersModelResponse> {
            override fun onFailure(call: Call<DevelopersModelResponse>?, t: Throwable?) {
                Log.v("retrofit", "call failed")
            }

            override fun onResponse(call: Call<DevelopersModelResponse>?, response: Response<DevelopersModelResponse>?) {

            }
        })

    }
}
