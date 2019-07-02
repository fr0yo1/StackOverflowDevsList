package com.stackoverflowdevslist.mainActivity.developerFragment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class DeveloperViewModelFactory(private val developerId: String, private val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DeveloperViewModel(developerId, application) as T
    }
}