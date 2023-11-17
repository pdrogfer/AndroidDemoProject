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

    suspend fun getUsers(): List<User>  = withContext(Dispatchers.IO){
        // TODO: add pagination
        // TODO: add loading and error cases
        val userList = arrayListOf<User>()
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
                        userList.addAll(it)
                    }
                }

        return@withContext userList
    }

    private val mockUsers = List(20) { i ->
        User(
            id = i,
            firstName = "User $i first name",
            lastName = "User $i last name",
            avatarUrl = "https://picsum.photos/200"
        )
    }
}