package com.example.jimbro.api.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest (

    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("age") val age: String,
    @SerializedName("height") val height: String,
    @SerializedName("weight") val weight: String,
    @SerializedName("intensity") val intensity: String,
    @SerializedName("gender") val gender: String,
)