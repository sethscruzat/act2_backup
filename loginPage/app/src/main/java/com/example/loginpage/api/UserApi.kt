package com.example.loginpage.api

import com.example.loginpage.api.model.UserDataModal
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserApi {
    @GET("/users")
    fun getUserList(): Call<List<UserDataModal>>

    @GET("/users/{id}")
    fun getUserData(@Path("id") id: Int): Call<UserDataModal>

    @POST("/login")
    fun authenticateUser(@Body map: HashMap<String, String>): Call<UserDataModal>
}