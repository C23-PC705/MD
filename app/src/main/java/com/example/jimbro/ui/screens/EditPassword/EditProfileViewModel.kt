package com.example.jimbro.ui.screens.EditPassword

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class EditProfileViewModel : ViewModel() {

    private val TAG = EditProfileViewModel::class.simpleName


    var registrationUIState = mutableStateOf(EditProfileUIState())

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: EditPasswordUIEvent) {
        when (event) {


            is EditPasswordUIEvent.passwordLama -> {
                registrationUIState.value = registrationUIState.value.copy(
                    passwordLama = event.passwordLama
                )
                printState()
            }

            is EditPasswordUIEvent.passwordTerbaru -> {
                registrationUIState.value = registrationUIState.value.copy(
                    passwordTerbaru = event.passwordBaru
                )
                printState()

            }
            is EditPasswordUIEvent.passwordTerbaruUlang -> {
                registrationUIState.value = registrationUIState.value.copy(
                    passwordTerbaruUlang = event.passwordBaruUlang
                )
                printState()

            }

            is EditPasswordUIEvent.RegisterButtonClicked -> {
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