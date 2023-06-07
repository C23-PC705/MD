package com.example.jimbro.ui.screens.EditProfile

import com.example.jimbro.ui.screens.Register.SignupUIEvent

sealed class EditProfileUIEvent{


    data class AgeChanged(val age:String) : EditProfileUIEvent()
    data class EmailChanged(val email:String): EditProfileUIEvent()
    data class PasswordChanged(val password: String) : EditProfileUIEvent()


    object RegisterButtonClicked : EditProfileUIEvent()
}
