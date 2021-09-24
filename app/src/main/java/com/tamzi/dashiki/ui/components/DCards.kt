package com.tamzi.dashiki.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tamzi.dashiki.ui.theme.Shapes
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


@ExperimentalMaterialApi
@Composable
fun ExpandableCard(
    title: String,
    titleFontSize: TextUnit = MaterialTheme.typography.h6.fontSize,
    titleFontWeight: FontWeight = FontWeight.Bold,
    description: String,
    descriptionFontSize: TextUnit = MaterialTheme.typography.subtitle1.fontSize,
    descriptionFontWeight: FontWeight = FontWeight.Normal,
    descriptionMaxLines: Int = 4,
    shape: Shape = Shapes.medium,
    padding: Dp = 12.dp
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = shape,
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = title,
                    fontSize = titleFontSize,
                    fontWeight = titleFontWeight,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
            if (expandedState) {
                Text(
                    text = description,
                    fontSize = descriptionFontSize,
                    fontWeight = descriptionFontWeight,
                    maxLines = descriptionMaxLines,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


