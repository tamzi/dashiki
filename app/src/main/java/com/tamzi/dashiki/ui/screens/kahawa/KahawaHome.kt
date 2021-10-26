package com.tamzi.dashiki.ui.screens.kahawa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.tamzi.dashiki.ui.theme.DashikiTheme
import com.tamzi.dashiki.ui.theme.darkGray

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DashikiTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = darkGray) {
                    KoffeeHomeScreen()
                }
            }
        }
    }
}

@Composable
fun KoffeeHomeScreen() {


}