package com.stackoverflowdevslist

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation.findNavController

class MainActivity : AppCompatActivity(),
        DevList.OnFragmentInteractionListener,
        DeveloperFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
