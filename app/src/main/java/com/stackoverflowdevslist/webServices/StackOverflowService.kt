package com.stackoverflowdevslist.webServices

import com.stackoverflowdevslist.developersRepository.DevelopersModel
import retrofit2.Call
import retrofit2.http.GET

interface StackOverflowService {
    @GET("/users?order=desc&sort=reputation&site=stackoverflow")
    fun getTopDevelopers(): Call<DevelopersModel>
}