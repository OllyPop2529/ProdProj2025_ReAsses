package com.example.productionproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class LoginActivityView {

}

@Composable
fun LoginPage()
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
        // Contains Icon and First Time Welcome Text
        Column(
            modifier = modifier
                .weight(6.5f)
                .padding(top = 15.dp)
                .background(Color.LightGray),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {



        }
        ////////////////////////////////////////////////////////

        // Bottom 1/3
        // Contains Password InputField + Confirmation Button
        Column(
            modifier = modifier
                .weight(3.5f)
                .padding(bottom = 15.dp)
                .background(Color.Red),
        )
        {

        }
        ////////////////////////////////////////////////////////
    }



}


@Composable
fun PasswordInputField()
{
    var data by remember { mutableStateOf("") }

    TextField(
        value =
        if(data == "") ""
        else data,
        onValueChange = {data = it},
        modifier = Modifier
            .padding(vertical = 10.dp, horizontal = 5.dp)
    )
}


@Composable
fun ConfirmButton(
    onClick: () -> Unit,
    modifier: Modifier,
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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxSize()
        )
        {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                lineHeight = 20.sp,
                modifier = Modifier
                    .weight(1.5f)
                    .padding(5.dp)

            )

        }

    }
}