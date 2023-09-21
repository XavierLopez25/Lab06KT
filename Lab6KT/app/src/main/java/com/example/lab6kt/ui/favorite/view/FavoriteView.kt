package com.example.lab6kt.ui.favorite.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.navigation.NavController
import com.example.lab6kt.navigation.TabsScreen
import com.example.lab6kt.ui.concerts.viewmodel.Concert
import com.example.lab6kt.ui.favorite.viewmodel.FavoriteViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayConcerts(navController: NavController) {
    val viewModel: FavoriteViewModel = viewModel()
    val concertDetails = viewModel.allConcerts

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TopBarWithBackButton(title = "Favorites", navController = navController)
        // Add the TopAppBar
        TopAppBar(
            title = {
                Text(text = "Your Favorites")
            },
        )

        LazyColumn(
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(concertDetails, key = { concert -> concert.bandConcertName }) { singleConcert ->
                ConcertItem(singleConcert)
                AddDivider()
            }
        }
    }
}

@Composable
fun ConcertItem(concertInfo: Concert) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = Modifier.width(2.dp))

        Image(
            painter = painterResource(id = concertInfo.bandImage),
            contentDescription = null,
            modifier = Modifier
                .size(52.dp)
                .align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = concertInfo.bandConcertName,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp
            )
            Text(
                text = concertInfo.concertPlace,
                fontSize = 17.sp
            )
            Text(
                text = concertInfo.concertDate,
                fontSize = 17.sp
            )
            Text(
                text = concertInfo.concertTime,
                fontSize = 17.sp
            )
            Text(
                text = concertInfo.concertPlace,
                fontSize = 17.sp
            )
        }

        Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = null,
            modifier = Modifier
                .size(22.dp)
                .align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(18.dp))
    }
}

@Composable
fun AddDivider() {
    Spacer(modifier = Modifier.height(17.dp))
    Divider(
        color = Color.Gray,
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 12.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithBackButton(title: String, navController: NavController) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(TabsScreen.Concerts.route) }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}