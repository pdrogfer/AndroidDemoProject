package com.pgf.demoproject.userdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import com.bumptech.glide.Glide
import com.pgf.demoproject.R
import com.pgf.demoproject.User
import com.pgf.demoproject.UserRepository
import com.pgf.demoproject.ui.LoadStatus

class UserDetailActivity : AppCompatActivity() {

    private lateinit var userDetailViewModel: UserDetailViewModel

    private val containerUserDetail: LinearLayoutCompat by lazy { findViewById(R.id.container_userdetail) }
    private val ivThumbnail: ImageView by lazy { findViewById(R.id.ivThumbnail) }
    private val tvName: TextView by lazy { findViewById(R.id.tvName) }
    private val tvSurname: TextView by lazy { findViewById(R.id.tvSurname) }
    private val progress: ProgressBar by lazy { findViewById(R.id.progressbar_userdetail) }
    private val textViewError: TextView by lazy { findViewById(R.id.tv_error_userdetail) }

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
                    progress.visibility = ProgressBar.VISIBLE
                    textViewError.visibility = TextView.GONE
                    containerUserDetail.visibility = LinearLayoutCompat.GONE
                }
                LoadStatus.SUCCESS -> {
                    setUI(dataState.data as User)
                    progress.visibility = ProgressBar.GONE
                    textViewError.visibility = TextView.GONE
                    containerUserDetail.visibility = LinearLayoutCompat.VISIBLE
                }
                LoadStatus.ERROR -> {
                    textViewError.text = dataState.errorMessage
                    progress.visibility = ProgressBar.GONE
                    textViewError.visibility = TextView.VISIBLE
                    containerUserDetail.visibility = LinearLayoutCompat.GONE
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