package com.example.loginpage.screens.display

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.loginpage.api.RetrofitConfig
import com.example.loginpage.api.model.ContentInfoModel
import com.example.loginpage.api.model.UserDataModal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class displayViewModel: ViewModel() {
    private val _objList = MutableStateFlow(emptyList<ContentInfoModel>())
    val objList = _objList.asStateFlow()

    fun getUserList(){
        val client: Call<List<UserDataModal>> = RetrofitConfig.getApiService().getUserList()
        client.enqueue(object: Callback<List<UserDataModal>> {
            override fun onResponse(call: Call<List<UserDataModal>>, response: Response<List<UserDataModal>>) {
                if(response.code() == 200){
                    val responseBody = response.body()

                    if(responseBody != null){
                        val tempObjList = mutableListOf<ContentInfoModel>()
                        for(i in responseBody.indices){
                            val firstName = responseBody[i].firstName
                            val lastName = responseBody[i].lastName
                            val fullName = "$firstName $lastName"

                            val userID = responseBody[i].id.toString()
                            tempObjList.add(i, ContentInfoModel(userID, fullName))
                        }
                        _objList.value = tempObjList
                    }
                }
            }

            override fun onFailure(call: Call<List<UserDataModal>>, t: Throwable) {
                Log.d("FAILURE", "Error", t.cause)
            }
        })
    }

}
