package com.tamzi.dashiki.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tamzi.dashiki.ui.components.DIconBtnBack
import com.tamzi.dashiki.ui.components.DSubtitle
import com.tamzi.dashiki.ui.components.PageTitle
import com.tamzi.dashiki.ui.theme.DashikiTheme
import com.tamzi.dashiki.ui.theme.gray
import com.tamzi.dashiki.ui.theme.utils.breathingSpace
import com.tamzi.dashiki.ui.theme.utils.separatorSpace

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DashikiTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ProductsScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsScreen() {
    DashikiTheme {
        Column (
            Modifier
                .fillMaxSize()
                .background(color = gray)
                .padding(horizontal = 13.dp, vertical =5.dp))
        {
            DIconBtnBack()
            breathingSpace()
            PageTitle("Products")
            separatorSpace()
            DSubtitle("Currently available in our outlets")


        }
    }
}




