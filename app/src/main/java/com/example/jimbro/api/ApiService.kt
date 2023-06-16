package com.example.jimbro.api

import com.example.jimbro.api.request.*
import com.example.jimbro.api.responses.LoginResponse
import com.example.jimbro.api.responses.RegisterResponse
import com.example.jimbro.api.responses.UpdateBMI
import com.example.jimbro.api.responses.UpdatePassword
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @Headers("Accept: application/json")
    @POST("users/register")
    fun createUser(@Body registerBody: RegisterRequest
    ): Call<RegisterResponse>

    @Headers("Accept: application/json")
    @POST("users/login")
    fun loginUser(@Body loginData: LoginRequest
    ): Call<LoginResponse>

    @Headers("Accept: application/json")
    @PUT("users/profile")
    fun updateProfile(@Body profilData: ProfileRequest
    ): Call<LoginResponse>

    @Headers("Accept: application/json")
    @PUT("users/bmi")
    fun updateBmi(@Body profilBmi: BmiRequest
    ): Call<UpdateBMI>

    @Headers("Accept: application/json")
    @PUT("users/password")
    fun updatePassword(@Body updatePasswordRequest: updatePasswordRequest
    ): Call<UpdatePassword>


}