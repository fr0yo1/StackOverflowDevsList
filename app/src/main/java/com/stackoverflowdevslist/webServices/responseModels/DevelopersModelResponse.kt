package com.stackoverflowdevslist.webServices.responseModels

import com.google.gson.annotations.SerializedName

class DevelopersModelResponse {
    @SerializedName("items")
    lateinit var developers: ArrayList<DeveloperModelResponse>
}