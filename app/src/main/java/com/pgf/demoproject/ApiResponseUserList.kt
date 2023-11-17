package com.pgf.demoproject

import com.google.gson.annotations.SerializedName

data class ApiResponseUserList(
    @SerializedName("data")
    val data: List<ApiResponseUser>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("support")
    val support: ApiResponseSupport,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)

data class ApiResponseUser(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_name")
    val lastName: String
)

data class ApiResponseSupport(
    @SerializedName("text")
    val text: String,
    @SerializedName("url")
    val url: String
)