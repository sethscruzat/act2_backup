package com.example.loginpage.screens.content

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.loginpage.api.RetrofitConfig
import com.example.loginpage.api.model.UserDataModal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class contentViewModel: ViewModel() {
    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _favFood = MutableStateFlow("")
    val favFood = _favFood.asStateFlow()


    fun getUserData(id: Int){
        val client: Call<UserDataModal> = RetrofitConfig.getApiService().getUserData(id)
        client.enqueue(object: Callback<UserDataModal> {
            override fun onResponse(call: Call<UserDataModal>, response: Response<UserDataModal>) {
                if(response.code() == 200){
                    val responseBody = response.body()

                    if (responseBody != null) {
                        val fullName = "${responseBody.firstName} ${responseBody.lastName}"
                        _name.value = fullName
                    }

                    if (responseBody != null) {
                        _email.value = responseBody.email
                    }

                    if (responseBody != null) {
                        _favFood.value = responseBody.favFood
                    }
                }
            }

            override fun onFailure(call: Call<UserDataModal>, t: Throwable) {
                Log.d("FAILURE", "Error ba talaga?", t.cause)
            }
        })
    }
}