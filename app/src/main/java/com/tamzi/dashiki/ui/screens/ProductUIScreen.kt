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


@Composable
fun ProductCard(product: Product) {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .height(230.dp)
    ) {
        val (
            bgImage,
            addButton,
            productImage,
            itemPrice) = createRefs()

        Surface(
            Modifier
                .fillMaxWidth(.5f)
                .fillMaxHeight()
                .clip(
                    shape = RoundedCornerShape(50.dp)
                )
                .constrainAs(bgImage) {
                    start.linkTo(
                        parent.start,
                        margin = 8.dp
                    )
                }, color = colorResource(id = product.color)
        ) {

        }

        Box(
            Modifier
                .height(46.dp)
                .width(46.dp)
                .background(color = white)
                .clip(shape = RoundedCornerShape(20.dp))
                .constrainAs(addButton) {
                    top.linkTo(parent.top)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Add Product Icon"
            )
        }

        Image(
            modifier = Modifier
                .fillMaxWidth(.6f)
                .constrainAs(productImage) {
                    centerVerticallyTo(parent)
                    start.linkTo(parent.start, margin = 30.dp)
                }
                .rotate(-25f),
            painter = painterResource(id = product.image),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.constrainAs(itemPrice) {
                end.linkTo(
                    parent.end,
                    margin = 30.dp
                )
                bottom.linkTo(parent.bottom, margin = 20.dp)
            }, text = product.price,
            style = TextStyle(
                color = black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

        )


    }

}

data class Product(val price: String, val color: Int, val image: Int)
