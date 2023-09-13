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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab6kt.navigation.Screen
import com.example.lab6kt.ui.concerts.view.ShowConcerts
import com.example.lab6kt.ui.details.view.DisplayConcertDetail
import com.example.lab6kt.ui.favorite.view.DisplayConcerts
import com.example.lab6kt.ui.profile.view.UserProfileAlternative
import com.example.lab6kt.ui.theme.Lab6KTTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab6KTTheme {
                AppNavigator()
            }
        }
    }
}

@Composable
fun RootView(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        ShowConcerts(navController) // If ShowConcerts needs the NavController
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Concerts.route) {
        composable(Screen.Concerts.route) {
            com.example.lab6kt.ui.concerts.view.RootView(navController)
        }
        composable(Screen.ConcertDetails.route + "/{concertId}") { backStackEntry ->
            val concertId = backStackEntry.arguments?.getString("concertId")
            if (concertId != null){
                DisplayConcertDetail(concertId)
            } else {
                Text(text = "Concert ID not provided.")
            }
        }
    }
}



