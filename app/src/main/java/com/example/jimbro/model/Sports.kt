package com.example.jimbro.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sports(
    val name: String,
    val reps: Int,
    val pictureUrl: Int,
    val Description: String
): Parcelable