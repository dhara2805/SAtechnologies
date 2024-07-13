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

class RegistrationViewModel  : ViewModel() {

    private val apiService = RetrofitClient.apiService

    fun registerUser(email: String, password: String): LiveData<String> {
        val result = MutableLiveData<String>()

        CoroutineScope(Dispatchers.IO).launch {

                val response = apiService.Registration(RegistrationModel(email, password))
                Log.d("res-->",response.toString())
                withContext(Dispatchers.Main) {
                    when (response.code()) {
                        200 -> {
                            result.postValue("Registration successful")
                        }

                        400 -> {
                            result.postValue("Registration failed: One or both fields are missing from JSON")
                        }

                        401 -> {
                            result.postValue("Registration failed: User already exists")
                        }

                        else -> {
                            result.postValue("Registration failed: Unexpected error occurred")
                        }
                    }
                }
        }
        return result
    }
}