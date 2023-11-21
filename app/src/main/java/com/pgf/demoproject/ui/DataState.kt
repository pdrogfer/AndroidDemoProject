package com.pgf.demoproject.ui

class DataState<T>(
    val state: LoadStatus = LoadStatus.LOADING,
    val data: T? = null,
    val errorMessage: String? = null,
)

enum class LoadStatus {
    LOADING,
    SUCCESS,
    ERROR
}
