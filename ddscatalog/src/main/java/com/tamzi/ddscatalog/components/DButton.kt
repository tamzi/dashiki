package com.tamzi.ddscatalog.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import com.tamzi.dds.atoms.color.black

@Composable
fun DIconBtnBack(){
    IconButton(onClick = { /*TODO*/ }) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "This is an icon",
            tint = black
        )
    }
}