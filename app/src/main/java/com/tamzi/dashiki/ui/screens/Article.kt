package com.tamzi.dashiki.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tamzi.dashiki.R
import com.tamzi.dashiki.ui.components.*
import com.tamzi.dashiki.ui.components.Product
import com.tamzi.dashiki.ui.theme.*
import com.tamzi.dashiki.ui.theme.utils.breathingSpace
import com.tamzi.dashiki.ui.theme.utils.separatorSpace

class ArticleActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DashikiTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = darkGray) {
                    ArticleScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleScreen() {


    DashikiTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = kahawaBrown)
                .padding(horizontal = 13.dp, vertical = 5.dp)
        )
        {
            DIconBtnBack()
            breathingSpace()
            PageTitle("Article")
            separatorSpace()
            DSubtitle("Currently available in our outlets")
            breathingSpace()
             }
    }
}
