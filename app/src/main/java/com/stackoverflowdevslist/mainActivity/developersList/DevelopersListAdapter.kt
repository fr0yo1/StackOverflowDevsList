package com.stackoverflowdevslist.mainActivity.developersList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.stackoverflowdevslist.R

class DevelopersListAdapter: RecyclerView.Adapter<DevelopersListAdapter.ViewHolder>() {

    private val items = ArrayList<DeveloperListElementViewModel>()

    fun setItems(items: ArrayList<DeveloperListElementViewModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.developer_list_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = items[position].displayName
        Picasso.get()
            .load(items[position].profileImage)
            .placeholder(R.drawable.user_placeholder)
            .into(holder.profileImage)
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var profileImage: ImageView = itemView.findViewById(R.id.profile_image)
    }
}