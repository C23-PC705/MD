package com.example.jimbro.ui.screens.Register

sealed class SignupUIEvent{

    data class FirstNameChanged(val name:String) : SignupUIEvent()
    data class AgeChanged(val age:String) : SignupUIEvent()
    data class EmailChanged(val email:String): SignupUIEvent()
    data class PasswordChanged(val password: String) : SignupUIEvent()
    data class HeightChanged(val height: String) : SignupUIEvent()
    data class WeightChanged(val weight: String) : SignupUIEvent()
    data class IntensityChanged(val intensity: String) : SignupUIEvent()
    data class genderChange(val gender:Boolean) : SignupUIEvent()

    object RegisterButtonClicked : SignupUIEvent()
}
