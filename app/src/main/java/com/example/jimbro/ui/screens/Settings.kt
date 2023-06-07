package com.example.jimbro.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jimbro.model.SportsData
import com.example.jimbro.ui.components.redbutton
import com.example.jimbro.ui.navigation.Screen
import com.example.jimbro.ui.theme.background
import com.example.jimbro.ui.theme.gray
import com.example.jimbro.ui.theme.primary
import com.example.jimbro.ui.theme.secondary
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable

fun Settings(

    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                    Text(text = "Settings Page",
                        modifier = Modifier.fillMaxWidth(),
                        color = primary,
                        textAlign = TextAlign.Center)
                },
                backgroundColor = secondary,
                modifier = Modifier,

                )
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .fillMaxSize()
            .background(background),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "BMI",
                modifier = Modifier,
                color = secondary,
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                lineHeight = 48.sp,
                fontWeight = FontWeight.Bold)
            Text(text = "23.8",
                modifier = Modifier,
                color = secondary,
                textAlign = TextAlign.Center, fontSize = 24.sp, lineHeight = 24.sp)
            Box(modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(secondary)) {
                Text(text = "Good",
                    modifier = Modifier
                        .padding(4.dp),
                    color = primary,
                    textAlign = TextAlign.Center, fontSize = 16.sp, lineHeight = 24.sp)
            }
            Spacer(modifier = modifier.height(60.dp))
            Button(modifier = Modifier, onClick = {
                navController.navigate(Screen.editBmi.route)
            }, colors = ButtonDefaults.buttonColors(backgroundColor = primary)) {
                Row(modifier = Modifier
                    .height(80.dp)
                    .padding(10.dp)
                    .width(300.dp),
                    Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Edit BMI",
                        modifier = Modifier,
                        color = secondary,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.Bold)
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Edit BMI"
                    )

                }

            }
            Spacer(modifier = modifier.height(30.dp))
            Button(modifier = Modifier, onClick = {
                navController.navigate(Screen.editUser.route)
            }, colors = ButtonDefaults.buttonColors(backgroundColor = primary)) {
                Row(
                    modifier = Modifier
                        .height(80.dp)
                        .padding(10.dp)
                        .width(300.dp),
                    Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = "Edit Profile",
                        modifier = Modifier,
                        color = secondary,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.Bold)
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Going To Edit Profile"
                    )


                }

            }
            Spacer(modifier = modifier.height(120.dp))


            redbutton(text = "Logout") {

            }

        }

    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    Settings()
}