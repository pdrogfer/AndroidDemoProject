package com.pgf.demoproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var usersViewModel: MainViewModel

    val rvUsers: RecyclerView by lazy {
        findViewById(R.id.rv_users)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUI(this)
    }

    private fun setUI(context: Context) {
        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rvUsers.adapter = AdapterUsers { user ->
            navigateToDetail(context, user)
        }

        usersViewModel = MainViewModel()
        usersViewModel.userList.observe(this) { userList ->
            (rvUsers.adapter as AdapterUsers).setUsers(userList)
        }
    }

    fun navigateToDetail(context: Context, user: User) {
        val intent = Intent(context, DetailActivity::class.java).apply {
            putExtra("user", user)
        }
        context.startActivity(intent)
    }
}