package com.example.jimbro.ui.screens.EditBmi

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class EditBmiViewModel : ViewModel() {

    private val TAG = EditBmiViewModel::class.simpleName


    var registrationUIState = mutableStateOf(EditBmiUIState())

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: EditBmiUIEvent) {
        when (event) {


            is EditBmiUIEvent.HeightChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    height = event.height
                )
                printState()
            }
            is EditBmiUIEvent.WeightChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    weight = event.weight
                )
                printState()
            }
            is EditBmiUIEvent.IntensityChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    intensity = event.intensity
                )
                printState()
            }

            is EditBmiUIEvent.RegisterButtonClicked -> {
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