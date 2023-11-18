package com.pgf.demoproject

import com.google.gson.annotations.SerializedName

data class ApiResponseUserList(
    @SerializedName("data")
    val data: List<ApiUser>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("support")
    val support: ApiSupport,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)

data class ApiResponseUser(
    @SerializedName("data")
    val data: ApiUser,
    @SerializedName("support")
    val support: ApiSupport
)

data class ApiUser(
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

data class ApiSupport(
    @SerializedName("text")
    val text: String,
    @SerializedName("url")
    val url: String
)