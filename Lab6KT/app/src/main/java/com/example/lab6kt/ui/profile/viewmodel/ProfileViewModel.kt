package com.example.lab6kt.ui.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class UserProfileViewModel : ViewModel() {
    // User data
    val userName = mutableStateOf("Xavier Lopez")
}