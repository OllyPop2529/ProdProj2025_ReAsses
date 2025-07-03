package com.example.pp2025_reasses.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class LocationActivityView
{


@Composable
fun LocationPage()
{
    val modifier : Modifier = Modifier
        .padding(horizontal = 15.dp)
        .fillMaxSize()
        .alpha(0.5f)



    Column(
        modifier = Modifier.fillMaxSize()
    )

    {
        // Top 2/3
        // Contains Connection Prompt / Device status
        Column(
            modifier = modifier
                .weight(6.5f)
                .padding(top = 15.dp)
                .background(Color.LightGray),
        )
        {

        }
        ////////////////////////////////////////////////////////

        // Lower 1/3
        // Contains setting + navigation
        Column(
            modifier = modifier
                .weight(3.5f)
                .padding(bottom = 15.dp)
                .background(Color.Red)

        )
        {

        }
        ////////////////////////////////////////////////////////
    }



}

    @Preview
    @Composable
    fun ActivityPreview()
    {
        LocationPage()
    }
}