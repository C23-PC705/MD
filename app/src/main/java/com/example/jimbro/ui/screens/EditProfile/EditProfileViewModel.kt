package com.example.jimbro.ui.screens.EditProfile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jimbro.ui.screens.Register.SignupUIEvent

class EditProfileViewModel : ViewModel() {

    private val TAG = EditProfileViewModel::class.simpleName


    var registrationUIState = mutableStateOf(EditProfileUIState())

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: EditProfileUIEvent) {
        when (event) {


            is EditProfileUIEvent.AgeChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    age = event.age
                )
                printState()
            }

            is EditProfileUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()

            }
            is EditProfileUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()

            }

            is EditProfileUIEvent.RegisterButtonClicked -> {
                signUp()
            }

        }
    }


    private fun signUp() {
        Log.d(TAG, "Inside_signUp")
        printState()

    }



    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }




}