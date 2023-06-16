package com.example.jimbro.ui.screens

import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jimbro.R
import com.example.jimbro.ui.components.MyTextFieldComponent
import com.example.jimbro.ui.components.bluebutton
import com.example.jimbro.ui.navigation.Screen
import com.example.jimbro.ui.screens.Register.SignupUIEvent
import com.example.jimbro.ui.screens.login.LoginViewModel
import com.example.jimbro.ui.theme.background
import com.nativemobilebits.loginflow.data.login.LoginUIEvent

@Composable
fun Login(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel,
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
                labelValue = "Email",
                onTextChanged = {
                    loginViewModel.onEventLogin(LoginUIEvent.EmailChanged(it))
                },
            )

            MyTextFieldComponent(
                labelValue = "Password",
                onTextChanged = {
                    loginViewModel.onEventLogin(LoginUIEvent.PasswordChanged(it))
                },
                password = true
            )

            bluebutton(text = "Login", onClick = {
                loginViewModel.onEventLogin(LoginUIEvent.LoginButtonClicked)
                navController.navigate(Screen.Home.route)
            })
        }
    }
}