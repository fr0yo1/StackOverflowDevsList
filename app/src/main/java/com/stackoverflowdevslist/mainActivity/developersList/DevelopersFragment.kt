package com.stackoverflowdevslist.mainActivity.developersList

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.stackoverflowdevslist.R

class DevelopersFragment : Fragment(), DevelopersListAdapter.OnItemClickListener {

    private lateinit var developersViewModel: DevelopersViewModel
    private lateinit var developersRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dev_list, container, false)
        developersRecyclerView = view.findViewById(R.id.developersRecyclerView)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        developersViewModel = ViewModelProviders.of(this).get(DevelopersViewModel::class.java)

        setObservers()
        setList()
    }

    private fun setObservers() {
        developersViewModel.developers.observe(this, Observer { devs ->
            devs?.let {
                (developersRecyclerView.adapter as DevelopersListAdapter).setItems(devs)
            }
        })
    }

    private fun setList() {
        val listAdapter = DevelopersListAdapter(this)
        developersRecyclerView.adapter = listAdapter
        developersRecyclerView.layoutManager = LinearLayoutManager(this.context)
    }

    override fun didSelectUser(userId: String, extras: FragmentNavigator.Extras?) {
        val args = Bundle().apply {
            this.putString("user_id", userId)
        }

        Navigation.findNavController(view!!).navigate(R.id.action_devList_to_developer, args, null, extras)
    }
}
