package com.stackoverflowdevslist

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.Navigation.findNavController

class SplashFragment : Fragment() {
    private lateinit var handler: Handler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash_sceen, container, false)
    }

    override fun onStart() {
        super.onStart()
        handler = Handler().apply {
                    postDelayed({
                    didFinishSplashScreen()
                    },1500)
        }
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacksAndMessages(null)
    }

    private fun didFinishSplashScreen() {
        val navOptions = NavOptions.Builder().setPopUpTo(R.id.splash_screen,true).build()
        findNavController(view!!).navigate(R.id.action_splash_screen_to_devList,null, navOptions)
    }
}
