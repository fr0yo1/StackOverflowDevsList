package com.stackoverflowdevslist.mainActivity.developerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionInflater
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.stackoverflowdevslist.R
import com.stackoverflowdevslist.mainActivity.developersList.getTransitionNameForDeveloperName
import com.stackoverflowdevslist.mainActivity.developersList.getTransitionNameForDeveloperProfile
import java.lang.Exception

private const val ARG_PARAM1 = "user_id"

class DeveloperFragment : androidx.fragment.app.Fragment() {
    private var param1: String? = null

    private lateinit var developerViewModel: DeveloperViewModel
    private lateinit var userName: TextView
    private lateinit var userProfileImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_developer, container, false)
        userName = view.findViewById(R.id.userName)
        userProfileImage = view.findViewById(R.id.userProfileImage)

        param1?.let { id ->
            userName.transitionName = getTransitionNameForDeveloperName(id)
            userProfileImage.transitionName = getTransitionNameForDeveloperProfile(id)
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }

        setTransitions()
    }

    private fun setTransitions() {
        postponeEnterTransition()
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        param1?.let {
            developerViewModel =
                ViewModelProviders.of(this, DeveloperViewModelFactory(param1!!, activity!!.application))
                    .get(DeveloperViewModel::class.java)
        }

        setObservers()
    }

    private fun setObservers() {
        developerViewModel.developer.observe(this, Observer { developer ->
            userName.text = developer.display_name

            Picasso.get()
                .load(developer.profile_image)
                .placeholder(R.drawable.user_placeholder)
                .into(userProfileImage, object: Callback {
                    override fun onSuccess() {
                        startPostponedEnterTransition()
                    }

                    override fun onError(e: Exception?) {
                        startPostponedEnterTransition()
                    }
                })

        })
    }

}
