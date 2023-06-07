package com.example.jimbro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jimbro.ui.navigation.Screen
import com.example.jimbro.ui.screens.*
import com.example.jimbro.ui.screens.EditBmi.editBmi
import com.example.jimbro.ui.screens.EditPassword.EditPassword
import com.example.jimbro.ui.screens.EditProfile.EditProfile
import com.example.jimbro.ui.theme.disableBottomBar
import com.example.jimbro.ui.theme.primary
import com.example.jimbro.ui.theme.secondary

//import com.example.jimbro.ui.theme.JimBroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JimBroApp()
        }
    }
}

@Composable
fun JimBroApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {


    var bottomBarState by rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()


    when (navBackStackEntry?.destination?.route) {
        "landing" -> {
            // Show BottomBar and TopBar
            bottomBarState = false

        }
        "register" -> {
            // Show BottomBar and TopBar
            bottomBarState = false

        }
        "settings" -> {
            // Show BottomBar and TopBar
            bottomBarState = true

        }
        "home" -> {
            // Show BottomBar and TopBar
            bottomBarState = true

        }
        "login" -> {
            // Hide BottomBar and TopBar
            bottomBarState = false

        }
    }
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = listOf(
                    BottomNavItem(
                        name = "Home",
                        route = "home",
                        icon = Icons.Default.Home
                    ),
                    BottomNavItem(
                        name = "Settings",
                        route = "settings",
                        icon = Icons.Default.Settings,
                    ),
                ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                },
                bottomBarState = bottomBarState
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Landing.route,
        ) {
            composable(Screen.Landing.route) {
                Landing(modifier = Modifier, navController = navController)
            }
            composable(Screen.Home.route) {
                Home(modifier = Modifier, navController = navController)
            }
            composable(Screen.Settings.route) {
                Settings(modifier = Modifier, navController = navController)

            }
            composable(Screen.Login.route) {
                Login(modifier = Modifier, navController = navController)

            }
            composable(Screen.Register.route) {
                Register(modifier = Modifier, navController = navController)
            }
            composable(Screen.editBmi.route) {
                editBmi(modifier = Modifier, navController = navController, onBackPressed= {navController.popBackStack()})
            }
            composable(Screen.editUser.route) {
                EditProfile(modifier = Modifier, navController = navController, onBackPressed= {navController.popBackStack()})
            }
            composable(Screen.editPassword.route) {
                EditPassword(modifier = Modifier, navController = navController, onBackPressed= {navController.popBackStack()})
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit,
    bottomBarState: Boolean,
) {
    if (bottomBarState) {
        val backStackEntry = navController.currentBackStackEntryAsState()
        BottomNavigation(
            modifier = modifier,
            backgroundColor = secondary,
            elevation = 5.dp
        ) {
            items.forEach { item ->
                val selected = item.route == backStackEntry.value?.destination?.route
                BottomNavigationItem(
                    selected = selected,
                    onClick = { onItemClick(item) },
                    selectedContentColor = primary,
                    unselectedContentColor = disableBottomBar,
                    icon = {
                        Column(horizontalAlignment = CenterHorizontally) {
                            if (selected) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )
                                Text(
                                    text = item.name,
                                    textAlign = TextAlign.Center,
                                    fontSize = 10.sp
                                )
                            } else {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )
                            }
                        }
                    }
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JimBroApp()
}