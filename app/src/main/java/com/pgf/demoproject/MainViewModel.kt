package com.pgf.demoproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val mockUsers = List(20) { i ->
        User(
            id = i,
            firstName = "first name",
            lastName = "last name",
            avatarUrl = "https://picsum.photos/200"
        )
    }

    val userList: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>()
    }

    init {
        loadRepositories()
    }

    private fun loadRepositories() {
        userList.value = mockUsers
    }
}