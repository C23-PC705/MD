package com.example.jimbro.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jimbro.R
import com.example.jimbro.ui.components.MyTextFieldComponent
import com.example.jimbro.ui.components.bluebutton

import com.example.jimbro.ui.navigation.Screen
import com.example.jimbro.ui.screens.Register.RegisterViewModel
import com.example.jimbro.ui.screens.Register.SignupUIEvent
import com.example.jimbro.ui.theme.background
import com.example.jimbro.ui.theme.secondary

@Composable
fun Register(
    registerViewModel: RegisterViewModel = viewModel(),
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Surface(modifier = Modifier
        .fillMaxSize()
        .background(background)
        .padding(28.dp)) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(background)
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Image(modifier = Modifier.size(200.dp),
                painter = painterResource(id = R.drawable.jimbrologo),
                contentDescription = "Jimbro Logo 2")

            MyTextFieldComponent(
                labelValue = "Name",
                onTextChanged = {
                    registerViewModel.onEvent(SignupUIEvent.FirstNameChanged(it))
                },
            )
            MyTextFieldComponent(
                labelValue = "Email",
                onTextChanged = {
                    registerViewModel.onEvent(SignupUIEvent.EmailChanged(it))
                },
            )
            MyTextFieldComponent(
                labelValue = "Password",
                onTextChanged = {
                    registerViewModel.onEvent(SignupUIEvent.PasswordChanged(it))
                },
            )
            MyTextFieldComponent(
                labelValue = "Age",
                onTextChanged = {
                    registerViewModel.onEvent(SignupUIEvent.AgeChanged(it))
                },
            )
            MyTextFieldComponent(
                labelValue = "Height",
                onTextChanged = {
                    registerViewModel.onEvent(SignupUIEvent.HeightChanged(it))
                },
            )
            MyTextFieldComponent(
                labelValue = "Weight",
                onTextChanged = {
                    registerViewModel.onEvent(SignupUIEvent.WeightChanged(it))
                },
            )
            MyTextFieldComponent(
                labelValue = "Intensity",
                onTextChanged = {
                    registerViewModel.onEvent(SignupUIEvent.IntensityChanged(it))
                },
            )
            MyTextFieldComponent(
                labelValue = "Gender",
                onTextChanged = {
                    registerViewModel.onEvent(SignupUIEvent.genderChange(false))
                },
            )
            bluebutton(text = "Register", onClick = {registerViewModel.onEvent(SignupUIEvent.RegisterButtonClicked)})
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview(navController: NavHostController = rememberNavController()) {
    Register ()
}