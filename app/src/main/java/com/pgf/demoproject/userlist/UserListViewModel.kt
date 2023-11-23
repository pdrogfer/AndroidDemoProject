package com.pgf.demoproject.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.pgf.demoproject.User
import com.pgf.demoproject.UserRepository
import com.pgf.demoproject.ui.LoadStatus
import com.pgf.demoproject.ui.DataState

class UserListViewModel(private val userRepository: UserRepository) : ViewModel() {

    val dataState: LiveData<DataState<List<User>>> = liveData {
        emit(DataState(state = LoadStatus.LOADING))

        val userList = userRepository.getUsers()

        if (userList != null) {
            emit(DataState(state = LoadStatus.SUCCESS, data = userList))
        } else {
            emit(DataState(state = LoadStatus.ERROR, errorMessage = "An error happened. Could not get users list. " +
                    "Please try again later"))
        }

    }
}