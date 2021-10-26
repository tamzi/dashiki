package com.tamzi.dashiki.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tamzi.dashiki.ui.theme.black
import com.tamzi.dashiki.ui.theme.paragraphTextColor


@Composable
fun PageTitle(pageTitle: String) {
    Text(
        text = "$pageTitle",
        style = TextStyle(
            color = paragraphTextColor,
            fontSize = 33.sp,
            fontWeight = FontWeight.W300,
        ),
        modifier = Modifier.fillMaxWidth()
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
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
