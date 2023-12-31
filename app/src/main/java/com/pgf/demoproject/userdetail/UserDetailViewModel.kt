package com.pgf.demoproject.userdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.pgf.demoproject.User
import com.pgf.demoproject.UserRepository
import com.pgf.demoproject.UserRepositoryImpl
import com.pgf.demoproject.ui.LoadStatus
import com.pgf.demoproject.ui.DataState

class UserDetailViewModel(
    private val userRepository: UserRepository,
    private val userId: Int,
) : ViewModel() {

    val dataState: LiveData<DataState<User>> = liveData {
        emit(DataState(state = LoadStatus.LOADING))

        val user = userRepository.getUser(userId = userId)

        if (user != null) {
            emit(DataState(state = LoadStatus.SUCCESS, data = user))
        } else {
            emit(DataState(state = LoadStatus.ERROR, errorMessage = "An error happened. Could not get user data. " +
                    "Please try again later."))
        }
    }
}
