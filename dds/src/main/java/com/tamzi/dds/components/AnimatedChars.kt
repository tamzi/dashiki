package com.tamzi.dds.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnimateCharTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(fontSize = 50.sp)
) {
    val scope = rememberCoroutineScope()
    val animatables = remember { mutableStateMapOf<Int, Animatable<Float, AnimationVector1D>>() }

    BasicTextField(
        value = value,
        onValueChange = { newValue ->
            val oldLength = value.length
            val newLength = newValue.length
            if (newLength > oldLength) {
                // Character added
                for (i in oldLength until newLength) {
                    animatables[i] = Animatable(0f)
                    scope.launch {
                        animatables[i]?.animateTo(1f, animationSpec = tween(300))
                    }
                }
            } else if (newLength < oldLength) {
                // Character(s) removed
                for (i in newLength until oldLength) {
                    scope.launch {
                        animatables[i]?.animateTo(0f, animationSpec = tween(300))
                        animatables.remove(i)
                    }
                }
            }
            onValueChange(newValue)
        },
        modifier = modifier,
        textStyle = textStyle.copy(color = Color.Transparent),
        decorationBox = { _ ->
            FlowRow {
                value.forEachIndexed { index, char ->
                    val scale = animatables[index]?.value ?: 1f
                    val alpha = animatables[index]?.value ?: 1f
                    Text(
                        text = "$char",
                        style = textStyle,
                        modifier = Modifier
                            .alpha(alpha)
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                                transformOrigin = TransformOrigin.Center
                            }
                    )
                }
            }
        }
    )
}