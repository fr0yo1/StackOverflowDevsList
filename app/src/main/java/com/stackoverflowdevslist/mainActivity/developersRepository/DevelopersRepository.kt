package com.stackoverflowdevslist.mainActivity.developersRepository

import android.content.Context

class DevelopersRepository {

    companion object {
        @Volatile private var INSTANCE: DevelopersRepository? = null

        fun getInstance(context: Context): DevelopersRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: DevelopersRepository().also { INSTANCE = it }
            }
    }
}