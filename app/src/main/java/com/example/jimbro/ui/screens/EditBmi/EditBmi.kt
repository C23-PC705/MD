package com.example.jimbro.ui.screens.EditBmi

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jimbro.R
import com.example.jimbro.model.EditBmi
import com.example.jimbro.model.SportsData
import com.example.jimbro.ui.components.MyTextFieldComponent
import com.example.jimbro.ui.components.MyTextFieldValueComponent
import com.example.jimbro.ui.components.bluebutton
import com.example.jimbro.ui.components.dropDownIntensity
import com.example.jimbro.ui.navigation.Screen
import com.example.jimbro.ui.screens.EditProfile.editProfileScreen
import com.example.jimbro.ui.screens.Register.SignupUIEvent
import com.example.jimbro.ui.screens.login.LoginViewModel
import com.example.jimbro.ui.screens.login.UiState
import com.example.jimbro.ui.screens.sportList
import com.example.jimbro.ui.theme.background
import com.example.jimbro.ui.theme.primary
import com.example.jimbro.ui.theme.secondary
import com.example.jimbro.ui.theme.white
import com.nativemobilebits.loginflow.data.login.LoginUIEvent

@Composable
fun editBmi(
    modifier: Modifier = Modifier,
    editBmiViewModel: LoginViewModel,
    navController: NavHostController = rememberNavController(),
    onBackPressed: () -> Boolean,
) {

    editBmiViewModel.uiStateBmi.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                editBmiViewModel.geteditBmi()
                Log.d("masuk Loading", "Loading Boss")
            }
            is UiState.Success -> {
                Log.d("Ada Data", uiState.data.toString())
                editBmiScreen(onBackPressed = onBackPressed, editBmiViewModel = editBmiViewModel, navController = navController, bmi = uiState.data)
            }

            is UiState.Error -> {
                Log.d("error", "Masukl Error")
            }

        }
    }

}

@Composable
fun editBmiScreen(
    modifier: Modifier = Modifier,
    editBmiViewModel: LoginViewModel,
    navController: NavHostController = rememberNavController(),
    onBackPressed: () -> Boolean,
    bmi: EditBmi

){
    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                    Text(text = "Edit BMI", modifier= Modifier.fillMaxWidth(), color = primary, textAlign = TextAlign.Start)
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
                    labelValue = "Height",
                    onTextChanged = {
                        editBmiViewModel.onEventBMI(EditBmiUIEvent.HeightChanged(it))
                    },
                    value = bmi.height.toString(),
                    enabled = true
                )
                MyTextFieldValueComponent(
                    labelValue = "Weight",
                    onTextChanged = {
                        editBmiViewModel.onEventBMI(EditBmiUIEvent.WeightChanged(it))
                    },
                    value = bmi.weight.toString(),
                    enabled = true
                )
//                MyTextFieldValueComponent(
//                    labelValue = "Intensity",
//                    onTextChanged = {
//                        editBmiViewModel.onEventBMI(EditBmiUIEvent.IntensityChanged(it))
//                    },
//                    value = bmi.intensity.toString(),
//                    enabled = true
//                )
                dropDownIntensity(
                    onTextChanged = {
                        editBmiViewModel.onEventBMI(EditBmiUIEvent.IntensityChanged(it))
                    },
                    value = bmi.intensity.toString()
                )
                bluebutton(text = "Update", onClick = {
                    editBmiViewModel.onEventBMI(EditBmiUIEvent.RegisterButtonClicked)
                    onBackPressed()
                })
            }
        }


    }
}