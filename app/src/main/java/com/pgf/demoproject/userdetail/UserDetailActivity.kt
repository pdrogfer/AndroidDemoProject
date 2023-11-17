package com.pgf.demoproject.userdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.pgf.demoproject.R
import com.pgf.demoproject.User

class UserDetailActivity : AppCompatActivity() {

    val ivThumbnail: ImageView by lazy {
        findViewById(R.id.ivThumbnail)
    }
    val tvName: TextView by lazy {
        findViewById(R.id.tvName)
    }
    val tvSurname: TextView by lazy {
        findViewById(R.id.tvSurname)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val user = intent.extras?.getParcelable<User>(User.KEY)

        user?.let { setUI(it) }
    }

    private fun setUI(user: User) {
        Glide.with(ivThumbnail.context)
            .load(user.avatarUrl)
            .into(ivThumbnail)

        tvName.text = user.firstName
        tvSurname.text = user.lastName
    }
}