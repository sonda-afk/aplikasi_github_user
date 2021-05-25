package com.sonda.bangkit.fundamentaldua.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sonda.bangkit.fundamentaldua.R
import com.sonda.bangkit.fundamentaldua.model.User

class FollowersAdapter() :
        RecyclerView.Adapter<FollowersAdapter.FollowersViewHolder>() {
    inner class FollowersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var username: TextView = itemView.findViewById(R.id.tv_item_name)
        var detail: TextView = itemView.findViewById(R.id.tv_item_detail)
    }

    var listUser = ArrayList<User>()
        set(listUser) {
            if (listUser.size > 0) {
                this.listUser.clear()
            }
            this.listUser.addAll(listUser)

            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): FollowersAdapter.FollowersViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return FollowersViewHolder(view)
    }

    override fun onBindViewHolder(holder: FollowersAdapter.FollowersViewHolder, position: Int) {
        val user = listUser[position]

        Glide.with(holder.itemView.context)
                .load(user.avatar)
                .apply(RequestOptions().override(350, 550))
                .into(holder.imgPhoto)

        holder.username.text = user.name
        holder.detail.text = user.username
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

}