package com.pgf.demoproject.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.pgf.demoproject.User
import com.pgf.demoproject.UserRepository
import kotlinx.coroutines.runBlocking

class UserListViewModel(private val userRepository: UserRepository) : ViewModel() {

    val userList: LiveData<List<User>> = liveData {
        emit(userRepository.getUsers())
    }
}