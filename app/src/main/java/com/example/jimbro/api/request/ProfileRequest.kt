package com.example.jimbro.api.request

import com.google.gson.annotations.SerializedName

data class ProfileRequest (
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("age") val age: String,
)