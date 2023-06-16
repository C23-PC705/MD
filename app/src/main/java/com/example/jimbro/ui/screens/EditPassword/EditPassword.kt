package com.example.jimbro.ui.screens.EditPassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jimbro.R
import com.example.jimbro.model.EditUser
import com.example.jimbro.ui.components.MyTextFieldComponent
import com.example.jimbro.ui.components.MyTextFieldValueComponent
import com.example.jimbro.ui.components.bluebutton
import com.example.jimbro.ui.navigation.Screen
import com.example.jimbro.ui.screens.EditProfile.EditProfileUIEvent

import com.example.jimbro.ui.screens.login.LoginViewModel
import com.example.jimbro.ui.screens.login.UiState
import com.example.jimbro.ui.theme.background
import com.example.jimbro.ui.theme.primary
import com.example.jimbro.ui.theme.secondary
import com.example.jimbro.ui.theme.white

@Composable
fun EditPassword(
    modifier: Modifier = Modifier,
    editProfileViewModel: LoginViewModel,
    navController: NavHostController = rememberNavController(),
    onBackPressed: () -> Boolean,
) {

    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                    Text(text = "Edit Profile",
                        modifier = Modifier.fillMaxWidth(),
                        color = primary,
                        textAlign = TextAlign.Start)
                },
                backgroundColor = secondary,
                modifier = Modifier,


                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back),
                            tint = white
                        )
                    }
                })
        }
    ) { innerPadding ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(24.dp)) {
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
                    labelValue = "Password Lama",
                    onTextChanged = {
                        editProfileViewModel.onEventChangePassword(EditPasswordUIEvent.passwordLama(
                            it))
                    },

                    )


                MyTextFieldComponent(
                    labelValue = "Password Terbaru",
                    onTextChanged = {
                        editProfileViewModel.onEventChangePassword(EditPasswordUIEvent.passwordTerbaru(
                            it))
                    },
                )


                MyTextFieldComponent(
                    labelValue = "Ulangi Password Terbaru",
                    onTextChanged = {
                        editProfileViewModel.onEventChangePassword(EditPasswordUIEvent.passwordTerbaruUlang(
                            it))
                    },
                )
                bluebutton(text = "Update", onClick = {
                    editProfileViewModel.onEventChangePassword(EditPasswordUIEvent.RegisterButtonClicked)
                    onBackPressed()
                })
            }
        }
    }
}