package com.tamzi.dashiki.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tamzi.dashiki.ui.theme.*


@Composable
fun PageTitle(pageTitle: String) {
    Text(
        text = "$pageTitle",
        color = black,
        fontSize = 23.sp,
        fontWeight = FontWeight.W900,
        fontFamily = SantanaFont,
    )
}

@Composable
fun DSubtitle(dSubtitle: String) {
    Text(
        text = "$dSubtitle",
        style = TextStyle(
            color = black,
            fontSize = 23.sp,
            fontWeight = FontWeight.W700,
            fontFamily = GraphikFont,
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun DParagraph(dParagraph: String) {
    Text(
        text = "$dParagraph",
        style = TextStyle(
            color = black,
            fontSize = 23.sp,
            fontWeight = FontWeight.W700,
            fontFamily = GraphikFont,
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
