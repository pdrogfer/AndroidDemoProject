package com.pgf.demoproject.userlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pgf.demoproject.R
import com.pgf.demoproject.User
import com.pgf.demoproject.UserRepository
import com.pgf.demoproject.userdetail.UserDetailActivity

class UserListActivity : AppCompatActivity() {

    private lateinit var usersViewModel: UserListViewModel

    private val rvUsers: RecyclerView by lazy {
        findViewById(R.id.rv_users)
    }

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
        usersViewModel.userList.observe(this) { userList ->
            (rvUsers.adapter as UserListAdapter).setUsers(userList)
        }
    }

    private fun navigateToDetail(context: Context, user: User) {
        val intent = Intent(context, UserDetailActivity::class.java).apply {
            putExtra(User.KEY, user)
        }
        context.startActivity(intent)
    }
}