package com.example.lab6kt.ui.concerts.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab6kt.ui.concerts.viewmodel.ConcertViewModel
import com.example.lab6kt.ui.concerts.viewmodel.Concert



@Composable
fun RootView() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        MainColumn()
    }
}

@Composable
fun MainColumn() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        TitleText("Todos los Eventos")
        ShowConcerts()
    }
}

@Composable
fun TitleText(title: String) {
    Text(
        text = title,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun ShowConcerts() {
    val viewModel: ConcertViewModel = viewModel()
    val allConcerts = viewModel.allConcerts

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        TitleText("Todos los Conciertos")
        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.weight(1f)) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Number of columns
                contentPadding = PaddingValues(16.dp)
            ) {
                items(allConcerts) { concert ->
                    Column {
                        DisplayConcertCard(concert)
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayConcertCard(concert: Concert) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(start = 1.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = concert.bandImage),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            InfoText(concert.bandConcertName, FontWeight.Bold, 18.sp)
            InfoText(concert.concertPlace, FontWeight.Normal, 16.sp)
        }
    }
}

@Composable
fun InfoText(text: String, weight: FontWeight, size: TextUnit) {
    Text(
        text = text,
        fontWeight = weight,
        fontSize = size,
        modifier = Modifier.padding(4.dp)
    )
}

