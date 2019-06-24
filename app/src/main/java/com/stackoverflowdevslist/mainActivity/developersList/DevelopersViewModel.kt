package com.stackoverflowdevslist.mainActivity.developersList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.stackoverflowdevslist.developersRepository.DevelopersRepository

class DevelopersViewModel constructor(application: Application): AndroidViewModel(application) {

    private var developersRepository = DevelopersRepository.getInstance(application)
    val developers = MediatorLiveData<ArrayList<DeveloperListElementViewModel>>()

    init {
        val developersList = developersRepository.developers

        developers.addSource(developersList) { devs ->
            val listElementViewModel = ArrayList<DeveloperListElementViewModel>()
            devs?.let {
                for (x in it)
                    listElementViewModel.add(DeveloperListElementViewModel(x))
            }

            developers.value = listElementViewModel
        }
    }
}