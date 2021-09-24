package com.tamzi.dashiki.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tamzi.dashiki.ui.theme.black
import com.tamzi.dashiki.ui.theme.white

@Composable
fun DProductCard(product: Product) {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .height(210.dp)
            .padding(3.dp)
    ) {
        val (
            bgImage,
            addButton,
            productImage,
            itemPrice) = createRefs()

        Surface(
            Modifier
                .fillMaxWidth(.7f)
                .fillMaxHeight()
                .clip(
                    shape = RoundedCornerShape(50.dp)
                )
                .constrainAs(bgImage) {
                    start.linkTo(
                        parent.start,
                        margin = 3.dp
                    )
                }, color = colorResource(id = product.color)
        ) {

        }

        Box(
            Modifier
                .height(42.dp)
                .width(42.dp)
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
                .fillMaxWidth(.7f)
                .constrainAs(productImage) {
                    centerVerticallyTo(parent)
                    start.linkTo(parent.start, margin = 21.dp)
                }
                .rotate(-21f),
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

