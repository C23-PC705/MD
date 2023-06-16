package com.example.jimbro.model

data class EditUser(
    val email: String,
    val age: Int,
    val password: String? = "",
)