package com.tamzi.dashiki.ui.screens

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.tamzi.dashiki.ui.components.ExpandableCard


@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun ExpandableCardPreview() {
    ExpandableCard(
        title = "My Title",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna " +
                "aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                "ullamco laboris nisi ut aliquip ex ea commodo consequat."
    )
}
