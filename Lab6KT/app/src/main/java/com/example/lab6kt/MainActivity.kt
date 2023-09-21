package com.example.lab6kt

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lab6kt.navigation.Screen
import com.example.lab6kt.ui.concerts.view.ShowConcerts
import com.example.lab6kt.ui.details.view.DisplayConcertDetail
import com.example.lab6kt.ui.favorite.view.DisplayConcerts
import com.example.lab6kt.ui.profile.view.UserProfileAlternative
import com.example.lab6kt.ui.theme.Lab6KTTheme
import com.example.lab6kt.navigation.TabsScreen
import com.example.lab6kt.navigation.allTabScreens

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab6KTTheme {
                MainActivityContent()
            }
        }
    }
}

@Composable
fun MainActivityContent() {
    val navController: NavHostController = rememberNavController()
    Column {
        TabsNavigation(navController)
        AppNavigator(navController)
    }
}

@Composable
fun RootView(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        ShowConcerts(navController) // If ShowConcerts needs the NavController
    }
}

@Composable
fun AppNavigator(navController: NavHostController) {
    NavHost(navController, startDestination = TabsScreen.Concerts.route) {
        composable(TabsScreen.Concerts.route) {
            RootView(navController)
        }
        composable(TabsScreen.Profile.route) {
            UserProfileAlternative(navController)
        }
        composable(TabsScreen.Favorites.route) {
            DisplayConcerts(navController)
        }
        composable(Screen.ConcertDetails.route + "/{concertId}") { backStackEntry ->
            val concertId = backStackEntry.arguments?.getString("concertId")
            if (concertId != null){
                DisplayConcertDetail(concertId, navController)
            } else {
                Text(text = "Concert ID not provided.")
            }
        }
    }
}

@Composable
fun TabsNavigation(navController: NavController) {
    val currentRoute = currentRoute(navController)

    TabRow(
        selectedTabIndex = allTabScreens.indexOfFirst { it.route == currentRoute }.takeIf { it >= 0 } ?: 0,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        allTabScreens.forEach { screen ->
            Tab(
                text = { Text(screen.title, color = Color.Black) }, // Set color explicitly
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(TabsScreen.Concerts.route) // Use the route directly
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}


