package com.stackoverflowdevslist.mainActivity.developerFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.app.Application


class DeveloperViewModelFactory(private val developerId: String, private val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DeveloperViewModel(developerId, application) as T
    }
}