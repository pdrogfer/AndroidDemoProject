package com.pgf.demoproject.userlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.pgf.demoproject.R
import com.pgf.demoproject.User
import com.pgf.demoproject.UserRepository
import com.pgf.demoproject.ui.LoadStatus
import com.pgf.demoproject.userdetail.UserDetailActivity

class UserListActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UserListViewModel

    private val rvUsers: RecyclerView by lazy { findViewById(R.id.rv_users) }
    private val progress: ProgressBar by lazy { findViewById(R.id.progressBar1) }
    private val textViewError: TextView by lazy { findViewById(R.id.tv_error) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUI()
        setViewModel()
    }

    private fun setUI() {
        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rvUsers.adapter = UserListAdapter { user ->
            navigateToDetail(this, user)
        }
    }

    private fun setViewModel() {
        usersViewModel = UserListViewModel(userRepository = UserRepository())
        usersViewModel.dataState.observe(this) { dataState ->
            when (dataState.state) {
                LoadStatus.LOADING -> {
                    rvUsers.visibility = RecyclerView.GONE
                    textViewError.visibility = TextView.GONE
                    progress.visibility = ProgressBar.VISIBLE
                }

                LoadStatus.SUCCESS -> {
                    (rvUsers.adapter as UserListAdapter).setUsers(dataState.data as List<User>)
                    rvUsers.visibility = RecyclerView.VISIBLE
                    progress.visibility = ProgressBar.GONE
                    textViewError.visibility = TextView.GONE
                }

                LoadStatus.ERROR -> {
                    textViewError.text = dataState.errorMessage
                    textViewError.visibility = TextView.VISIBLE
                    progress.visibility = ProgressBar.GONE
                    rvUsers.visibility = RecyclerView.GONE
                }
            }
        }
    }

    private fun navigateToDetail(context: Context, user: User) {
        val intent = Intent(context, UserDetailActivity::class.java).apply {
            putExtra(User.KEY, user.id)
        }
        context.startActivity(intent)
    }
}