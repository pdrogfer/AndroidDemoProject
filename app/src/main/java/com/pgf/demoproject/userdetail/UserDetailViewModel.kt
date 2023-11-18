package com.pgf.demoproject.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.pgf.demoproject.User
import com.pgf.demoproject.UserRepository

class UserDetailViewModel(private val userRepository: UserRepository, userId: Int): ViewModel() {

    // TODO: consider error case of user not found
    val user: LiveData<User> = liveData {
        userRepository.getUser(userId = userId)?.let { emit(it) }
    }

}
