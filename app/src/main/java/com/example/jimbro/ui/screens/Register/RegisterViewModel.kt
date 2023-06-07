package com.example.jimbro.ui.screens.Register

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.xml.validation.Validator

class RegisterViewModel : ViewModel() {

    private val TAG = RegisterViewModel::class.simpleName


    var registrationUIState = mutableStateOf(RegistrationUIState())

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: SignupUIEvent) {
        when (event) {
            is SignupUIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    name = event.name
                )
                printState()
            }

            is SignupUIEvent.AgeChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    age = event.age
                )
                printState()
            }

            is SignupUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()

            }
            is SignupUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()

            }

            is SignupUIEvent.HeightChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    height = event.height
                )
                printState()
            }
            is SignupUIEvent.WeightChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    weight = event.weight
                )
                printState()
            }
            is SignupUIEvent.IntensityChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    intensity = event.intensity
                )
                printState()
            }
            is SignupUIEvent.genderChange -> {
                registrationUIState.value = registrationUIState.value.copy(
                    gender = event.gender
                )
                printState()
            }

            is SignupUIEvent.RegisterButtonClicked -> {
                signUp()
            }

        }
    }


    private fun signUp() {
        Log.d(TAG, "Inside_signUp")
        printState()
        createUser(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password
        )
    }



    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }


    private fun createUser(email: String, password: String) {

        signUpInProgress.value = true

    }

}