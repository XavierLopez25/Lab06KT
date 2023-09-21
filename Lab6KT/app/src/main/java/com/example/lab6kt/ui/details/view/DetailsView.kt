package com.example.lab6kt.ui.details.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab6kt.ui.details.viewmodel.DetailsViewModel
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab6kt.navigation.Screen
import com.example.lab6kt.ui.concerts.view.RootView
import com.example.lab6kt.ui.concerts.viewmodel.Concert

@Composable
fun ConcertLayout(concert: Concert, navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBarWithBackButton(title = concert.bandConcertName, navController = navController)
        TopImage(imageResource = concert.bandImage)
        ConcertBox(concert)
    }
}

@Composable
fun TopImage(imageResource: Int) {
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
        ,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ConcertBox(concert: Concert) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)

    ) {
        BackgroundImage(imageResource = concert.bandImage)
        ConcertDetails(concert)
    }
}

@Composable
fun BackgroundImage(imageResource: Int) {
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.9f)
            .graphicsLayer {
                renderEffect = BlurEffect(70f, 70f)
            },
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ConcertDetails(concert: Concert) {
    // Wrap the Column in a Box to allow it to be on top of the background image
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .alpha(alpha = 100f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ConcertTop(concert)
            ConcertBottom()
        }
    }
}

@Composable
fun ConcertTop(concert: Concert) {
    Column(
        modifier = Modifier
            .alpha(alpha = 100f),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text("Place: ${concert.concertPlace}", fontSize = 18.sp, color = Color.White)
        Text("${concert.bandConcertName}", fontWeight = FontWeight.Bold,fontSize = 25.sp, color = Color.White)
        ConcertDateTime(concert)
        Text("About:", fontSize = 16.sp, color = Color.White)
        Text(concert.aboutBand, fontSize = 16.sp, color = Color.White)
    }
}

@Composable
fun ConcertDateTime(concert: Concert) {
    Row(

        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .alpha(alpha = 100f),
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Filled.Favorite, contentDescription = null, tint = Color.White)
            Text("  Date: ${concert.concertDate}", fontSize = 16.sp, color = Color.White)
        }
        Text("Hour: ${concert.concertTime}", fontSize = 16.sp, color = Color.White)
    }
}

@Composable
fun ConcertBottom() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .alpha(alpha = 100f),
    ) {
        ConcertButton("Favorite")
        Spacer(modifier = Modifier.width(16.dp))
        ConcertButton("Buy")
    }
}

@Composable
fun ConcertButton(label: String) {
    Button(
        onClick = { /* Do nothing */ },
        colors = ButtonDefaults.buttonColors(Color(0xFFFFCFC5), contentColor = Color.Black)
    ) {
        Text(label)
    }
}



@Composable
fun DisplayConcertDetail(concertId: String, navController: NavController) {
    // Obtain the DetailsViewModel
    val detailsViewModel: DetailsViewModel = viewModel()

    // Fetch the concert details using the provided concertId
    detailsViewModel.fetchConcertDetails(concertId)

    // Observe the selectedConcert from the ViewModel
    val concert = detailsViewModel.selectedConcert

    if (concert != null) {
        ConcertLayout(concert, navController)
    } else {
        // Handle the case where the concert details are not available
        // This could be a Text widget displaying an error message, for example
        Text(text = "Concert details not available.")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithBackButton(title: String, navController: NavController) {
    androidx.compose.material3.TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}



