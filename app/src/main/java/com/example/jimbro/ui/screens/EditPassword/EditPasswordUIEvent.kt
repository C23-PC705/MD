package com.example.jimbro.ui.screens.EditPassword

sealed class EditPasswordUIEvent{


    data class passwordLama(val passwordLama:String) : EditPasswordUIEvent()
    data class passwordTerbaru(val passwordBaru:String): EditPasswordUIEvent()
    data class passwordTerbaruUlang(val passwordBaruUlang: String) : EditPasswordUIEvent()


    object RegisterButtonClicked : EditPasswordUIEvent()
}
