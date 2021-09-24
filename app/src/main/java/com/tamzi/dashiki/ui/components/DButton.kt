package com.tamzi.dashiki.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.tamzi.dashiki.ui.theme.black

@Composable
fun DIconBtnBack(){
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = "This is an icon",
            tint = black
        )
    }
}