package com.stackoverflowdevslist.webServices

import com.stackoverflowdevslist.webServices.responseModels.DevelopersModelResponse
import retrofit2.Call
import retrofit2.http.GET
import java.util.ArrayList

interface StackOverflowService {
    @GET("/users?order=desc&sort=reputation&site=stackoverflow")
    fun getTopDevelopers(): Call<DevelopersModelResponse>
}