package com.example.jimbro.api.responses

import com.google.gson.annotations.SerializedName

data class UpdateBMI(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("bmi")
	val bmi: Any? = null
)
