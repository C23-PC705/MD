package com.example.jimbro.api.request

import com.google.gson.annotations.SerializedName

data class updatePasswordRequest (
    @SerializedName("oldPassword") val oldPassword: String,
    @SerializedName("newPassword") val newPassword: String?,

    )