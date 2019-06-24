package com.stackoverflowdevslist.mainActivity.developersList

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stackoverflowdevslist.R

class DevelopersFragment : androidx.fragment.app.Fragment() {

    private lateinit var developersViewModel: DevelopersViewModel
    private lateinit var developersRecyclerView: androidx.recyclerview.widget.RecyclerView

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
        val listAdapter = DevelopersListAdapter()
        developersRecyclerView.adapter = listAdapter
        developersRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this.context)
    }
}
