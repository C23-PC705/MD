package com.example.jimbro.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jimbro.R

import androidx.compose.material.Surface
import androidx.compose.ui.unit.dp

import com.example.jimbro.ui.components.yellowbutton
import com.example.jimbro.ui.navigation.Screen
import com.example.jimbro.ui.theme.secondary

@Composable
fun Landing(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(secondary)
            .padding(28.dp)


    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(secondary),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Image(modifier = Modifier.size(150.dp),
                painter = painterResource(id = R.drawable.jimbro),
                contentDescription = "Jimbro Logo")
            Row(modifier = Modifier.padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
                yellowbutton(text = "Register", onClick = {navController.navigate(Screen.Register.route)})
                Spacer(modifier = Modifier.width(30.dp))
                yellowbutton(text = "Login", onClick = {navController.navigate(Screen.Login.route)})
                    

            }
        }
    }

}


@Preview
@Composable
fun LoginScreenPreview() {
    Landing()
}