package com.example.lab6kt.ui.details.viewmodel

import com.example.lab6kt.ui.concerts.viewmodel.Concert
import com.example.lab6kt.ui.concerts.viewmodel.ConcertViewModel
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class DetailsViewModel : ViewModel() {

    // This will hold the details of the selected concert
    var selectedConcert by mutableStateOf<Concert?>(null)

    // Assuming you have a repository or data source to fetch concerts
    // For simplicity, I'm using the sample data from ConcertViewModel
    private val allConcerts = ConcertViewModel().allExampleConcerts()

    // Fetch concert details by its ID (or name in this case)
    fun fetchConcertDetails(id: String) {
        selectedConcert = allConcerts.find { it.bandConcertName == id }
    }

    fun getConcertById(id: String?): Concert? {
        return allConcerts.find { it.bandConcertName == id }
    }
}

