package com.stackoverflowdevslist.mainActivity.developersList

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.FragmentNavigator
import com.squareup.picasso.Picasso
import com.stackoverflowdevslist.R

fun getTransitionNameForDeveloperName(id: String): String {
    return "developerTextView$id"
}

fun getTransitionNameForDeveloperProfile(id: String): String {
    return "developerImageView$id"
}

class DevelopersListAdapter(private var listener: OnItemClickListener):
    RecyclerView.Adapter<DevelopersListAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun didSelectUser(userId: String, extras: FragmentNavigator.Extras? = null)
    }

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
        holder.view.tag = items[position].userId

        holder.name.transitionName = getTransitionNameForDeveloperName(items[position].userId)
        holder.profileImage.transitionName = getTransitionNameForDeveloperProfile(items[position].userId)

        val callback =  { _: View ->
            val extras = FragmentNavigator.Extras.Builder()
                .addSharedElement(holder.profileImage, holder.profileImage.transitionName)
                .addSharedElement(holder.name, holder.name.transitionName).build()

            listener.didSelectUser(holder.view.tag as String, extras)
        }

        holder.view.setOnClickListener(callback)
        holder.name.setOnClickListener(callback)

        Picasso.get()
            .load(items[position].profileImage)
            .placeholder(R.drawable.user_placeholder)
            .into(holder.profileImage)
    }

    override fun getItemCount() = items.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var view = itemView
        var name: TextView = itemView.findViewById(R.id.name)
        var profileImage: ImageView = itemView.findViewById(R.id.profile_image)
    }
}