package com.example.jimbro.ui.screens.EditProfile

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.datastore.dataStore
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jimbro.R
import com.example.jimbro.model.EditUser
import com.example.jimbro.ui.components.MyTextFieldValueComponent
import com.example.jimbro.ui.components.bluebutton
import com.example.jimbro.ui.navigation.Screen
import com.example.jimbro.ui.screens.login.LoginViewModel
import com.example.jimbro.ui.screens.login.UiState
import com.example.jimbro.ui.theme.background
import com.example.jimbro.ui.theme.primary
import com.example.jimbro.ui.theme.secondary
import com.example.jimbro.ui.theme.white

@Composable
fun EditProfile(
    modifier: Modifier = Modifier,
    editProfileViewModel: LoginViewModel,
    navController: NavHostController = rememberNavController(),
    onBackPressed: () -> Boolean,
) {


    editProfileViewModel.uiStateUser.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                editProfileViewModel.getedituser()
                Log.d("masuk Loading", "Loading Boss")
            }
            is UiState.Success -> {
                Log.d("Ada Data", uiState.data.toString())
                editProfileScreen(onBackPressed = onBackPressed, editProfileViewModel = editProfileViewModel, navController = navController, profile = uiState.data)
            }

            is UiState.Error -> {
                Log.d("error", "Masukl Error")
            }

        }
    }

}

@Composable
fun editProfileScreen(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Boolean,
    editProfileViewModel: LoginViewModel,
    navController: NavHostController = rememberNavController(),
    profile: EditUser
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


                MyTextFieldValueComponent(
                    labelValue = "Email",
                    onTextChanged = {
                        editProfileViewModel.onEventEditProfile(EditProfileUIEvent.EmailChanged(it))
                    },
                    value = profile.email,
                    enabled = true
                )
                Row(modifier = Modifier) {

                    MyTextFieldValueComponent(
                        labelValue = "Masukan Password Anda Untuk mengkonfirmasi",
                        onTextChanged = {
                            editProfileViewModel.onEventEditProfile(EditProfileUIEvent.PasswordChanged(
                                it))
                        },
                        value = profile.password!!,
                        enabled = true,
                        password = true
                    )
                }
                MyTextFieldValueComponent(
                    labelValue = "Umur",
                    onTextChanged = {
                        editProfileViewModel.onEventEditProfile(EditProfileUIEvent.AgeChanged(it))
                    },
                    value = profile.age.toString(),
                    enabled = true
                )
                bluebutton(text = "Update", onClick = {
                    editProfileViewModel.onEventEditProfile(EditProfileUIEvent.RegisterButtonClicked)
                    onBackPressed()
                })
            }
        }
    }
}