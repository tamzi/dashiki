package com.tamzi.dashiki.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tamzi.dashiki.R
import com.tamzi.dashiki.ui.components.*
import com.tamzi.dashiki.ui.components.Product
import com.tamzi.dds.DashikiTheme
import com.tamzi.dds.atoms.color.gray
import com.tamzi.dds.utils.breathingSpace13
import com.tamzi.dds.utils.separatorSpace10


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
            breathingSpace13()
            PageTitle("Products")
            separatorSpace10()
            DSubtitle("Currently available in our outlets")
            breathingSpace13()
            for (p in productList){
                DProductCard(product = p)
            }
        }
    }
}


