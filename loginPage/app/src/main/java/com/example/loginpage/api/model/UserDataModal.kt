package com.example.loginpage.api.model

import com.google.gson.annotations.SerializedName
data class UserDataModal(
    @SerializedName("id")
    val id: Int,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("favorite_food")
    val favFood: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)
