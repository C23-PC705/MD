package com.example.jimbro.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jimbro.R
import com.example.jimbro.model.SportsData
import com.example.jimbro.ui.theme.background
import com.example.jimbro.ui.theme.primary
import com.example.jimbro.ui.theme.secondary

@Composable
fun Home(

    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                    Text(text = "Sport Page",
                        modifier = Modifier.fillMaxWidth(),
                        color = primary,
                        textAlign = TextAlign.Center)
                },
                backgroundColor = secondary,
                modifier = Modifier,

                )
        }
    ) { innerPadding ->
        Box(modifier = modifier
            .padding(innerPadding)) {
            LazyColumn {
                itemsIndexed(SportsData.sports) { index, sport ->

                    sportList(index = index,
                        name = sport.name,
                        photoUrl = sport.pictureUrl,
                        reps = sport.reps)
                }
            }
        }


    }
}

@Composable
fun sportList(
    index: Int,
    name: String,
    photoUrl: Int,
    reps: Int,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Column(

        modifier = modifier
            .clickable {
//            navController.navigate(Screen.Detail.createRoute(index))
            }
            .padding(28.dp)
            .background(background),
    ) {
        Image(modifier = Modifier
            .size(400.dp),
            painter = painterResource(id = photoUrl),
            contentDescription = name)


        Text(text = name,
            modifier = Modifier, fontWeight = FontWeight.Bold)
        Text(text = reps.toString(),
            modifier = Modifier, fontWeight = FontWeight.Medium)
    }
}

@Preview
@Composable
fun HomePreview() {
    Home()
}