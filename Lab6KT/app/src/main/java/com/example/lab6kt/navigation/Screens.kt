package com.example.lab6kt.navigation

sealed class Screen(val route: String) {
    object Concerts : Screen("concerts")
    object ConcertDetails : Screen("concertDetails/{concertId}")
}