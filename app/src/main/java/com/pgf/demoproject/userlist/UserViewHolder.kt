package com.pgf.demoproject.userlist

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pgf.demoproject.R
import com.pgf.demoproject.User

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val userContainer: LinearLayout = itemView.findViewById(R.id.user_container)
    val userThumbnail: ImageView = itemView.findViewById(R.id.user_thumbnail)
    val userFirstName: TextView = itemView.findViewById(R.id.user_firstname)
    val userLastName: TextView = itemView.findViewById(R.id.user_lastname)

    fun bindData(user: User, onUserSelected: (user: User) -> Unit) {
        Glide.with(userThumbnail.context)
            .load(user.avatarUrl)
            .into(userThumbnail)
            //.preload(50, 50)
        userFirstName.setText(user.firstName)
        userLastName.setText(user.lastName)

        userContainer.setOnClickListener {
            onUserSelected(user)
        }
    }
}