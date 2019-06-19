package com.stackoverflowdevslist.developersRepository

import com.google.gson.annotations.SerializedName

class DevelopersModel {
    @SerializedName("items")
    lateinit var developers: ArrayList<DeveloperModel>
}