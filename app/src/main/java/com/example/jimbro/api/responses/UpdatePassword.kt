package com.example.jimbro.api.responses

import com.google.gson.annotations.SerializedName

data class UpdatePassword(

	@field:SerializedName("message")
	val message: String? = null
)
