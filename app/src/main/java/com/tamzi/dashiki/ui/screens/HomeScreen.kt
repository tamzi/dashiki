package com.tamzi.dashiki.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tamzi.dds.DashikiTheme
import com.tamzi.dds.atoms.color.dashikWhite
import com.tamzi.dds.utils.breathingSpace13
import com.tamzi.dds.utils.breathingSpace26
import com.tamzi.dds.utils.separatorSpace5

@Preview(showBackground = true)
@Composable
fun HomeScreenActivity() {
    DashikiTheme() {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = dashikWhite)
                .padding(horizontal = 13.dp, vertical = 5.dp)
        ) {
            breathingSpace26()
            separatorSpace5()
            breathingSpace13()
        }
    }
}