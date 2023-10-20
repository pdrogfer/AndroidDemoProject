package com.pgf.demoproject

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainActivityState())
    val uiState = _uiState.asStateFlow()

    private val mockRepositories = List(20) { i ->
        Repository(
            name = "Repository $i",
            description = "Description $i",
            stars = i * 10,
            forks = i * 5
        )
    }

    init {
        loadRepositories()
    }

    private fun loadRepositories() {
        _uiState.value = MainActivityState(isLoading = true)
        _uiState.value = MainActivityState(repositories = mockRepositories)
    }
}