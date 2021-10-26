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
import com.tamzi.dashiki.R
import com.tamzi.dashiki.ui.components.*
import com.tamzi.dashiki.ui.components.Product
import com.tamzi.dashiki.ui.theme.*
import com.tamzi.dashiki.ui.theme.utils.breathingSpace
import com.tamzi.dashiki.ui.theme.utils.separatorSpace

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DashikiTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = darkGray) {
                    ProductsScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsScreen() {
    val productList = listOf(
        Product("USD 400",color = R.color.darkBluish, R.drawable.nike_air_max),
        Product("USD 400",color = R.color.bluish, R.drawable.nike_walk),
        Product("USD 400",color = R.color.warmRed, R.drawable.female_high_heels),
        Product("USD 400",color = R.color.bluish, R.drawable.converse),

    )


    DashikiTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = gray)
                .padding(horizontal = 13.dp, vertical = 5.dp)
        )
        {
            DIconBtnBack()
            breathingSpace()
            PageTitle("Products")
            separatorSpace()
            DSubtitle("Currently available in our outlets")
            breathingSpace()
            for (p in productList){
                DProductCard(product = p)

            }            }
    }
}
