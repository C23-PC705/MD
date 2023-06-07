package com.example.jimbro.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.jimbro.ui.theme.primary
import com.example.jimbro.ui.theme.white

@Composable
fun MyTextFieldComponent(
    labelValue: String,
    onTextChanged: (String) -> Unit,
    password: Boolean = false

) {

    val textValue = remember {
        mutableStateOf("")
    }
    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            ,
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = primary,
            focusedLabelColor = primary,
            cursorColor = primary,
            backgroundColor = white
        ),
        keyboardOptions = if (!password)  KeyboardOptions(imeAction = ImeAction.Next) else  KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        },
    )
}
@Composable
fun MyTextFieldValueComponent(
    labelValue: String,
    onTextChanged: (String) -> Unit,
    value: String,
    enabled: Boolean = true,
    password: Boolean = false,

    ) {

    val textValue = remember {
        mutableStateOf(value)
    }
    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
        ,

        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = primary,
            focusedLabelColor = primary,
            cursorColor = primary,
            backgroundColor = white
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
            onTextChanged(it)
        },
    )
}

@Preview
@Composable
fun MyTextFieldComponentPreview() {
    MyTextFieldComponent(labelValue = "Test", {})
}

