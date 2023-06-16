package com.example.jimbro.api.request

import com.google.gson.annotations.SerializedName

data class BmiRequest (
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("intensity") val intensity: String,
)