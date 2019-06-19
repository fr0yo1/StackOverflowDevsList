package com.stackoverflowdevslist.mainActivity.developersList

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stackoverflowdevslist.R

class DevelopersFragment : Fragment() {

    private lateinit var developersViewModel: DevelopersViewModel
    private lateinit var developersRecyclerView: RecyclerView

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
        developersViewModel.getDevelopers().observe(this, Observer { devs ->
            devs?.let {
                (developersRecyclerView.adapter as DevelopersListAdapter).setItems(devs)
            }
        })
    }

    private fun setList() {
        val listAdapter = DevelopersListAdapter()
        developersRecyclerView.adapter = listAdapter
        developersRecyclerView.layoutManager = LinearLayoutManager(this.context)
    }
}
