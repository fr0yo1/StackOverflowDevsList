package com.stackoverflowdevslist.mainActivity.developerFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.stackoverflowdevslist.developersRepository.DeveloperModel
import com.stackoverflowdevslist.developersRepository.DevelopersRepository

class DeveloperViewModel constructor(developerId: String, application: Application): AndroidViewModel(application) {

    private val developersRepository = DevelopersRepository.getInstance(application)
    val developer: LiveData<DeveloperModel>

    init {
        developer = developersRepository.getDeveloperWith(developerId)
    }
}