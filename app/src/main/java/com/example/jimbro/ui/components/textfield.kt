package com.example.jimbro.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jimbro.ui.theme.primary
import com.example.jimbro.ui.theme.white

@Composable
fun MyTextFieldComponent(
    labelValue: String,
    onTextChanged: (String) -> Unit,
    password: Boolean = false,

    ) {

    val textValue = remember {
        mutableStateOf("")
    }
    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        label = { Text(text = labelValue) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = primary,
            focusedLabelColor = primary,
            cursorColor = primary,
            backgroundColor = white
        ),
        keyboardOptions = if (!password) KeyboardOptions(imeAction = ImeAction.Next) else KeyboardOptions(
            keyboardType = KeyboardType.Password),
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
            .fillMaxWidth(),

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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun dropDownIntensity(onTextChanged: (String) -> Unit, value: String = "") {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var gender by remember {
        mutableStateOf(value)
    }
    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it }) {
        TextField(value = if(gender == "") "Intensity" else gender,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = primary,
                focusedLabelColor = primary,
                cursorColor = primary,
                backgroundColor = white
            ), modifier = Modifier.fillMaxWidth().padding(0.dp, 5.dp))

        ExposedDropdownMenu(expanded = isExpanded,
            onDismissRequest = { isExpanded = false }) {
            DropdownMenuItem(

                onClick = {
                    isExpanded = false
                    gender = "little"
                    onTextChanged("little")
                },
            ) {
                Text(text = "little")
            }

            DropdownMenuItem(

                onClick = {
                    isExpanded = false
                    gender = "Moderate "
                    onTextChanged("Moderate")
                },
            ) {
                Text(text = "Moderate")
            }

            DropdownMenuItem(

                onClick = {
                    isExpanded = false
                    gender = "High"
                    onTextChanged("High")
                },
            ) {
                Text(text = "High")
            }
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun dropDownGender(onTextChanged: (String) -> Unit,) {
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var gender by remember {
        mutableStateOf("")
    }
    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = { isExpanded = it }) {
        TextField(value = if(gender == "") "Gender" else gender,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = primary,
                focusedLabelColor = primary,
                cursorColor = primary,
                backgroundColor = white
            ), modifier = Modifier.fillMaxWidth().padding(0.dp, 5.dp))

        ExposedDropdownMenu(expanded = isExpanded,
            onDismissRequest = { isExpanded = false }) {
            DropdownMenuItem(

                onClick = {
                    isExpanded = false
                    gender = "Male"
                    onTextChanged("Male")
                },
            ) {
                Text(text = "Male")
            }
            DropdownMenuItem(

                onClick = {
                    isExpanded = false
                    gender = "Female"
                    onTextChanged("Female")
                },
            ) {
                Text(text = "Female")
            }
        }

    }
}

@Preview
@Composable
fun MyTextFieldComponentPreview() {
    MyTextFieldComponent(labelValue = "Test", {})
}




