package com.example.lab6kt.ui.concerts.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.lab6kt.R


class ConcertViewModel : ViewModel() {

    // Sample data for concerts
    val allConcerts by mutableStateOf(allExampleConcerts())

    fun allExampleConcerts(): List<Concert> = listOf(

        Concert("Concierto 1", "Lugar 1", "Fecha 1", "Hora 1", "Informacion 1", R.drawable.concert1),
        Concert("Concierto 2", "Lugar 2", "Fecha 2", "Hora 2", "Informacion 2", R.drawable.concert2),
        Concert("Concierto 3", "Lugar 3", "Fecha 3", "Hora 3", "Informacion 3", R.drawable.concert3),
        Concert("Concierto 4", "Lugar 4", "Fecha 4", "Hora 4", "Informacion 4", R.drawable.concert4),
        Concert("Concierto 5", "Lugar 5", "Fecha 5", "Hora 5", "Informacion 5", R.drawable.concert1),
        Concert("Concierto 6", "Lugar 6", "Fecha 6", "Hora 6", "Informacion 6", R.drawable.concert2),
        Concert("Concierto 7", "Lugar 7", "Fecha 7", "Hora 7", "Informacion 7", R.drawable.concert3),
        Concert("Concierto 8", "Lugar 8", "Fecha 8", "Hora 8", "Informacion 8", R.drawable.concert4)

    )
}

