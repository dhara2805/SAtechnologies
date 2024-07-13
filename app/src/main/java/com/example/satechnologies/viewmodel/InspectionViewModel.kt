package com.example.satechnologies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.satechnologies.api.RetrofitClient
import com.example.satechnologies.model.Category
import com.example.satechnologies.model.Inspection
import com.example.satechnologies.model.InspectionModel1
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import retrofit2.HttpException

class InspectionViewModel : ViewModel() {

    private val apiService = RetrofitClient.apiService

    private val _inspection = MutableLiveData<InspectionModel1?>()
    val inspection: MutableLiveData<InspectionModel1?> = _inspection

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun startInspection() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.startInspection()
                if (response.isSuccessful) {
                    val inspectionResponse = response.body()
                    if (inspectionResponse != null) {
                        _inspection.postValue(inspectionResponse)
                        _categories.postValue(inspectionResponse.inspection.survey.categories)
                    } else {
                        _error.postValue("Inspection data not found")
                    }
                } else {
                    _error.postValue("Failed to start inspection: ${response.code()}")
                }
            } catch (e: HttpException) {
                _error.postValue("Network error: ${e.message()}")
            } catch (e: Exception) {
                _error.postValue("Unexpected error: ${e.message}")
            }
        }
    }
}