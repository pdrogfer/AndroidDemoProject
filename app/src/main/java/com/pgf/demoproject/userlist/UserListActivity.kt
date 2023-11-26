package com.pgf.demoproject.userlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pgf.demoproject.R
import com.pgf.demoproject.User
import com.pgf.demoproject.UserRepository
import com.pgf.demoproject.UserRepositoryImpl
import com.pgf.demoproject.ui.DataState
import com.pgf.demoproject.ui.LoadStatus
import com.pgf.demoproject.userdetail.UserDetailActivity
import org.koin.android.ext.android.inject

class UserListActivity : AppCompatActivity() {

    private val userRepository: UserRepository by inject()
    private lateinit var usersViewModel: UserListViewModel

    private val rvUsers: RecyclerView by lazy { findViewById(R.id.rv_users) }
    private val progress: ProgressBar by lazy { findViewById(R.id.progressbar_userlist) }
    private val textViewError: TextView by lazy { findViewById(R.id.tv_error_userlist) }

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
        usersViewModel = UserListViewModel(userRepository = userRepository)
        usersViewModel.dataState.observe(this) { dataState ->
            loadState(dataState)
        }
    }

    private fun loadState(dataState: DataState<List<User>>) =
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

    private fun navigateToDetail(context: Context, user: User) {
        val intent = Intent(context, UserDetailActivity::class.java).apply {
            putExtra(User.KEY, user.id)
        }
        context.startActivity(intent)
    }
}