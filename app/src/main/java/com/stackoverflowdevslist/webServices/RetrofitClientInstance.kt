package com.stackoverflowdevslist.webServices

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {

    private const val baseURL = "https://api.stackexchange.com"
    public val retrofit: Retrofit by lazy {
        retrofit2.Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}