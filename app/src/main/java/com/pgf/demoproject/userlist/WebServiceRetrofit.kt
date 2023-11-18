package com.pgf.demoproject.userlist

import com.pgf.demoproject.ApiResponseUser
import com.pgf.demoproject.ApiResponseUserList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebServiceRetrofit {

    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): Response<ApiResponseUserList>

    @GET("users/{id}")
    suspend fun getUser(@Path("id") id: Int): Response<ApiResponseUser>
}