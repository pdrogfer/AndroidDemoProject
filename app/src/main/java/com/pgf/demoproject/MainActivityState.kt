package com.pgf.demoproject

data class MainActivityState(
    val repositories: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)