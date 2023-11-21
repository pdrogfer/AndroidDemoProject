package com.pgf.demoproject.userdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.pgf.demoproject.R
import com.pgf.demoproject.User
import com.pgf.demoproject.UserRepository
import com.pgf.demoproject.ui.LoadStatus

class UserDetailActivity : AppCompatActivity() {

    private lateinit var userDetailViewModel: UserDetailViewModel

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

        val userId = intent.extras?.getInt(User.KEY)

        setViewModel(userId)
    }

    private fun setViewModel(userId: Int?) {
        userDetailViewModel = UserDetailViewModel(
            userRepository = UserRepository(),
            userId = userId ?: 0
        )

        // TODO implement UI for all states
        userDetailViewModel.dataState.observe(this) { dataState ->
            when (dataState.state) {
                LoadStatus.LOADING -> {
                    // TODO()
                    Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
                }
                LoadStatus.SUCCESS -> {
                    setUI(dataState.data as User)
                }
                LoadStatus.ERROR -> {
                    // TODO()
                    Toast.makeText(this, dataState.errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setUI(user: User) {
        Glide.with(ivThumbnail.context)
            .load(user.avatarUrl)
            .into(ivThumbnail)

        tvName.text = user.firstName
        tvSurname.text = user.lastName
    }
}