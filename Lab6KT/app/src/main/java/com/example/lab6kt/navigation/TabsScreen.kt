package com.example.lab6kt.navigation

sealed class TabsScreen(val route: String, val title: String) {
    object Concerts : TabsScreen("concerts", "Concerts")
    object Profile : TabsScreen("profile", "Profile")
    object Favorites : TabsScreen("favorites", "Favorites")
}

val allTabScreens = listOf(TabsScreen.Concerts, TabsScreen.Profile, TabsScreen.Favorites)
