package com.example.satechnologies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.satechnologies.api.RetrofitClient
import com.example.satechnologies.model.RegistrationModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {
    private val apiService = RetrofitClient.apiService

    fun loginUser(email: String, password: String): LiveData<String> {
        val result = MutableLiveData<String>()

        CoroutineScope(Dispatchers.IO).launch {

            val response = apiService.Login(RegistrationModel(email, password))
            Log.d("res-->",response.toString())
            withContext(Dispatchers.Main) {
                when (response.code()) {
                    200 -> {
                        result.postValue("login was successful")
                    }

                    400 -> {
                        result.postValue("Login failed: email or password fields are missing from the JSON")
                    }

                    401 -> {
                        result.postValue("Login failed: user does not exist or the credentials are incorrect")
                    }

                    else -> {
                        result.postValue("Login failed: Unexpected error occurred")
                    }
                }
            }
        }
        return result
    }
}