package com.pgf.demoproject

import android.util.Log
import com.pgf.demoproject.userlist.WebServiceRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class UserRepository {

    private val TAG = "WebRepository"
    val URL_BASE = "https://reqres.in/api/"

    private var webServiceRetrofit: WebServiceRetrofit

    init {
        /* TODO: move data source (web/local) to a data source, for example WebDataSource
         * See AppDataSource
         */
        val retrofitInstance: Retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        webServiceRetrofit = retrofitInstance.create()
    }

    suspend fun getUsers(): List<User>? {
        // TODO: add pagination
        // TODO: add loading and error cases
        var userList: List<User>? = null
        val response = webServiceRetrofit.getUsers(1)
        if (response.isSuccessful) {
                    val apiResponseUserList = response.body()
                    val users = apiResponseUserList?.data?.map { apiUser ->
                        User(
                            id = apiUser.id,
                            firstName = apiUser.firstName,
                            lastName = apiUser.lastName,
                            avatarUrl = apiUser.avatar
                        )
                    }
                    users?.let {
                        userList = it
                    }
                }

        return userList
    }

    suspend fun getUser(userId: Int): User? {
        var user: User? = null
        val response = webServiceRetrofit.getUser(userId)
        if (response.isSuccessful) {
            val apiResponseUser = response.body()
            user = apiResponseUser?.data?.let { apiUser ->
                User(
                    id = apiUser.id,
                    firstName = apiUser.firstName,
                    lastName = apiUser.lastName,
                    avatarUrl = apiUser.avatar
                )
            }
        }
        return user
    }
}