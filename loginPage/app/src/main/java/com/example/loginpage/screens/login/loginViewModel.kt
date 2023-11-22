package com.example.loginpage.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpage.api.RetrofitConfig
import com.example.loginpage.api.model.UserDataModal
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class loginViewModel: ViewModel(){
    fun authenticateUser(map: HashMap<String, String>, resp: (Response<UserDataModal>) -> Unit){
        viewModelScope.launch {
            val client: Call<UserDataModal> = RetrofitConfig.getApiService().authenticateUser(map)
            Log.d("OPERATION", "authenticateUser() is executing!")
            client.enqueue(object: Callback<UserDataModal> {
                override fun onResponse(call: Call<UserDataModal>, response: Response<UserDataModal>) {
                    if(response.code() == 200){
                        resp.invoke(response)
                    }

                    if(response.code() == 404){
                        resp.invoke(response)
                    }
                }

                override fun onFailure(call: Call<UserDataModal>, t: Throwable) {
                    Log.d("FAILURE", "Error", t.cause)
                }
            })
        }
    }
}
