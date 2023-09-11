package com.example.lab6kt

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.lab6kt.ui.concerts.view.ShowConcerts
import com.example.lab6kt.ui.theme.Lab6KTTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab6KTTheme {
                RootView()
            }
        }
    }
}

@Composable
fun RootView() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        ShowConcerts()
    }
}



