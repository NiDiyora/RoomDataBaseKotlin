package com.example.roomdatabaseexample.Adapters

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.data.User
import com.example.roomdatabaseexample.databinding.UserListItemBinding
import de.hdodenhof.circleimageview.CircleImageView

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    var userlist: List<User> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setuser(userlist: List<User>) {
        this.userlist = userlist
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val userListItemBinding: UserListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_list_item,
            parent,
            false
        )
        return UserViewHolder(userListItemBinding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user: User = userlist[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    class UserViewHolder(userListItemBinding: UserListItemBinding) :
        RecyclerView.ViewHolder(userListItemBinding.root) {
        val userListItemBinding = userListItemBinding

        fun bind(user: User) {
            userListItemBinding.user = user
        }
    }

}