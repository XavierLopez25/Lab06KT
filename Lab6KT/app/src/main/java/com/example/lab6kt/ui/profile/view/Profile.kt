package com.example.lab6kt.ui.profile.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab6kt.R

@Preview
@Composable
fun UserProfileAlternative() {
    val userName = "Xavier Lopez"

    Column(modifier = Modifier.fillMaxSize()) {
        UserHeader(userName = userName)
        UserOptions()
    }
}

@Composable
fun UserHeader(userName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentAlignment = Alignment.Center
    ) {
        BackgroundImage()
        UserInfo(userName)
    }
}

@Composable
fun BackgroundImage() {
    Image(
        painter = painterResource(id = R.drawable.profile_bg),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun UserInfo(userName: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        UserIcon()
        UserName(userName)
    }
}

@Composable
fun UserIcon() {
    Icon(
        imageVector = Icons.Default.AccountCircle,
        contentDescription = null,
        modifier = Modifier.size(150.dp),
        tint = Color.White
    )
}

@Composable
fun UserName(userName: String) {
    Text(
        text = userName,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White
    )
}

@Composable
fun UserOptions() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(30.dp)) {
            ProfileOption(icon = Icons.Default.AccountCircle, title = "Edit Profile", trailingIcon = Icons.Default.ArrowDropDown)
            ProfileOption(icon = Icons.Default.Lock, title = "Reset Password", trailingIcon = Icons.Default.ArrowDropDown)
            ProfileOption(icon = Icons.Default.Person, title = "Notifications", toggle = true)
            ProfileOption(icon = Icons.Default.Star, title = "Favorites", trailingIcon = Icons.Default.ArrowDropDown)
        }
    }
}

@Composable
fun ProfileOption(icon: ImageVector, title: String, trailingIcon: ImageVector? = null, toggle: Boolean = false) {
    val isChecked = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OptionTitle(icon, title)
        OptionAction(trailingIcon, toggle, isChecked)
    }
}

@Composable
fun OptionTitle(icon: ImageVector, title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = icon, contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = title, fontSize = 18.sp)
    }
}

@Composable
fun OptionAction(trailingIcon: ImageVector?, toggle: Boolean, isChecked: MutableState<Boolean>) {
    if (toggle) {
        Switch(checked = isChecked.value, onCheckedChange = { isChecked.value = it })
    } else {
        Icon(
            imageVector = trailingIcon ?: Icons.Default.ArrowDropDown,
            contentDescription = null,
            modifier = Modifier.rotate(180f)
        )
    }
}