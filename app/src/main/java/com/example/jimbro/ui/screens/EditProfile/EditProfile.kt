package com.example.jimbro.ui.screens.EditProfile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
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
import com.example.jimbro.ui.components.MyTextFieldValueComponent
import com.example.jimbro.ui.components.bluebutton
import com.example.jimbro.ui.navigation.Screen
import com.example.jimbro.ui.theme.background
import com.example.jimbro.ui.theme.primary
import com.example.jimbro.ui.theme.secondary
import com.example.jimbro.ui.theme.white

@Composable
fun EditProfile(
    modifier: Modifier = Modifier,
    editProfileViewModel: EditProfileViewModel= viewModel(),
    navController: NavHostController = rememberNavController(),
    onBackPressed: () -> Boolean,
) {
    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                    Text(text = "Edit Profile", modifier= Modifier.fillMaxWidth(), color = primary, textAlign = TextAlign.Start)
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


                MyTextFieldValueComponent(
                    labelValue = "Email",
                    onTextChanged = {
                        editProfileViewModel.onEvent(EditProfileUIEvent.EmailChanged(it))
                    },
                    value = "christopher.naibaho14@gmail.com",
                    enabled = true
                )
                Row(modifier = Modifier) {

                    MyTextFieldValueComponent(
                        labelValue = "Password",
                        onTextChanged = {
                            editProfileViewModel.onEvent(EditProfileUIEvent.PasswordChanged(it))
                        },
                        value = "Tompels14",
                        enabled = true,
                        password = true
                    )
                    IconButton(onClick = { navController.navigate(Screen.editUser.route) }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = stringResource(id = R.string.edit),
                            tint = secondary
                        )
                    }
                }
                MyTextFieldValueComponent(
                    labelValue = "Umur",
                    onTextChanged = {
                        editProfileViewModel.onEvent(EditProfileUIEvent.AgeChanged(it))
                    },
                    value = "37",
                    enabled = true
                )
                bluebutton(text = "Update", onClick = {
                    editProfileViewModel.onEvent(EditProfileUIEvent.RegisterButtonClicked)
                    onBackPressed()
                })
            }
        }
    }
}