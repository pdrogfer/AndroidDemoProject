package com.pgf.demoproject

import com.pgf.demoproject.userlist.WebServiceRetrofit
import kotlinx.coroutines.delay
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

interface UserRepository {
    suspend fun getUsers(): List<User>?
    suspend fun getUser(userId: Int): User?
}

class UserRepositoryImpl : UserRepository {

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

    override suspend fun getUsers(): List<User>? {

        // TODO: add pagination
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
        // Artificial delay to show Loading state
        delay(1000)

        // Return null to show error state
        return userList
    }

    override suspend fun getUser(userId: Int): User? {
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

        // artificial delay to show Loading state
        Thread.sleep(1000)

        // Return null to show error state
        return user
    }
}