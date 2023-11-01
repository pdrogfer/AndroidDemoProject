package com.pgf.demoproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterUsers(val onUserSelected: (user: User) -> Unit) : RecyclerView.Adapter<UserViewHolder>() {

    private var users = arrayListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindData(users[position], onUserSelected)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun setUsers(userList: List<User>) {
        users = userList as ArrayList<User>
    }

}
