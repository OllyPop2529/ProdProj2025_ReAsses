package com.example.pp2025_reasses.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pp2025_reasses.R


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
                .weight(8f)
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
                .weight(2f)
                .padding(bottom = 15.dp)
                .background(Color.Red)

        )
        {
            val buttonModifier : Modifier = Modifier
                .padding(all = 25.dp)
                //.fillMaxWidth(0.8f)
                //.fillMaxHeight(0.4f)
                .fillMaxSize()
                //.weight(1f)

            NavigationButton(
                onClick = {},
                modifier = buttonModifier,
                text = "Settings",
                image = R.drawable.setting)
        }
        ////////////////////////////////////////////////////////
    }

}

    @Composable
    fun NavigationButton(
        onClick: () -> Unit,
        modifier: Modifier,
        image: Int,
        text : String
    ) {
        Button(
            onClick = { onClick() },
            shape = RoundedCornerShape(corner = CornerSize(10.dp)),
            modifier = modifier,
            elevation = ButtonDefaults.buttonElevation(5.dp,0.dp)
        )
        {
            Row(
                modifier = Modifier
            )
            {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)

                )
                Text(
                    text = text,
                    textAlign = TextAlign.Center,
                    fontSize = 36.sp,
                    lineHeight = 20.sp,
                    modifier = Modifier
                        .weight(1.5f)
                        .padding(5.dp)
                        .align(Alignment.CenterVertically)

                )

            }

        }
    }

    @Preview
    @Composable
    fun ActivityPreview()
    {
        LocationPage()
    }
