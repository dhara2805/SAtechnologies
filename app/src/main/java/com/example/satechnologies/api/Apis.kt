package com.example.satechnologies.api

import com.example.satechnologies.model.InspectionModel1
import com.example.satechnologies.model.RegistrationModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Apis {
    @POST("api/register")
    suspend fun Registration(@Body userDate: RegistrationModel): Response<ResponseBody>

    @POST("api/login")
    suspend fun Login(@Body userDate: RegistrationModel): Response<ResponseBody>

    @GET("/api/inspections/start")
    suspend fun startInspection(): Response<InspectionModel1>
}