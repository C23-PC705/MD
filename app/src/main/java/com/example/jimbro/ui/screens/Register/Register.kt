package com.example.jimbro.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jimbro.R
import com.example.jimbro.ui.components.*

import com.example.jimbro.ui.navigation.Screen
import com.example.jimbro.ui.screens.Register.SignupUIEvent
import com.example.jimbro.ui.screens.login.LoginViewModel
import com.example.jimbro.ui.theme.background
import com.example.jimbro.ui.theme.secondary

@Composable
fun Register(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    registerViewModel: LoginViewModel
) {
    val context = LocalContext.current
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
                    registerViewModel.onEventRegister(SignupUIEvent.FirstNameChanged(it))
                },
            )
            MyTextFieldComponent(
                labelValue = "Email",
                onTextChanged = {
                    registerViewModel.onEventRegister(SignupUIEvent.EmailChanged(it))
                },
            )
            MyTextFieldComponent(
                labelValue = "Password",
                onTextChanged = {
                    registerViewModel.onEventRegister(SignupUIEvent.PasswordChanged(it))
                },
            )
            MyTextFieldComponent(
                labelValue = "Age",
                onTextChanged = {
                    registerViewModel.onEventRegister(SignupUIEvent.AgeChanged(it))
                },
            )
            MyTextFieldComponent(
                labelValue = "Height",
                onTextChanged = {
                    registerViewModel.onEventRegister(SignupUIEvent.HeightChanged(it))
                },
            )
            MyTextFieldComponent(
                labelValue = "Weight",
                onTextChanged = {
                    registerViewModel.onEventRegister(SignupUIEvent.WeightChanged(it))
                },
            )
            dropDownIntensity(
                onTextChanged = {
                    registerViewModel.onEventRegister(SignupUIEvent.IntensityChanged(it))
                },
            )
            dropDownGender(

                onTextChanged = {
                    registerViewModel.onEventRegister(SignupUIEvent.genderChange(it))
                },
            )
            bluebutton(text = "Register", onClick = {
                registerViewModel.onEventRegister(SignupUIEvent.RegisterButtonClicked)
                Toast.makeText(context, if(registerViewModel.signUpInProgress.value) "register Berhasil" else "Register Tidak Berhasil", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            })
        }
    }
}

//@Preview
//@Composable
//fun RegisterScreenPreview(navController: NavHostController = rememberNavController(), registerViewModel: LoginViewModel) {
//    Register (registerViewModel = registerViewModel)
//}