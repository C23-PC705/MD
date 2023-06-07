package com.example.jimbro.ui.screens.EditBmi

sealed class EditBmiUIEvent{


    data class HeightChanged(val height: String) : EditBmiUIEvent()
    data class WeightChanged(val weight: String) : EditBmiUIEvent()
    data class IntensityChanged(val intensity: String) : EditBmiUIEvent()


    object RegisterButtonClicked : EditBmiUIEvent()
}
