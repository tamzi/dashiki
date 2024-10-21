package com.tamzi.dashiki.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tamzi.dashiki.ui.components.*
import com.tamzi.dds.DashikiTheme
import com.tamzi.dds.atoms.color.dashikiBrown
import com.tamzi.dds.utils.breathingSpace13
import com.tamzi.dds.utils.separatorSpace5

@Preview(showBackground = true)
@Composable
fun ArticleScreen() {


    DashikiTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = dashikiBrown)
                .padding(horizontal = 13.dp, vertical = 5.dp)
        )
        {
            DIconBtnBack()
            breathingSpace13()
            PageTitle("Article")
            separatorSpace5()
            DSubtitle("Currently available in our outlets")
            breathingSpace13()
        }
    }
}



