package com.example.pp2025_reasses.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pp2025_reasses.R


@Composable
fun LoginPage(
    onLogin: (String) -> Unit,
    onFirst: (String) -> Unit,
)
{
    val modifier : Modifier = Modifier
        .padding(horizontal = 15.dp)
        .fillMaxSize()
        .alpha(0.5f)

    var inputPassword by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }

    var isCreating by remember { mutableStateOf(false)}

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
            verticalArrangement = Arrangement.SpaceBetween
        )
        {
            //Application Icon
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "Application Icon",
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.9f)
            )


            //First Time Prompt
            Text(
                text =
                if (isCreating) stringResource(R.string.first_time)
                else stringResource(R.string.enter_password),
                textAlign = TextAlign.Justify,
                fontSize = 20.sp,
                lineHeight = 55.sp,
                modifier = Modifier
                    .weight(0.2f)
                    .padding(vertical = 10.dp)


            )
        }
        ////////////////////////////////////////////////////////

        // Bottom 1/3
        // Contains Password InputField + Confirmation Button
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .weight(3.5f)
                .padding(bottom = 15.dp)
                .background(Color.Red),
        )
        {
            //Password Field
            TextField(
                value = inputPassword,
                onValueChange = {inputPassword= it},
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .padding(vertical = 35.dp)
            )

            //Confirm Button
            Button(
                onClick = {
                if (isCreating) { onFirst(inputPassword) }
                else { onLogin(inputPassword) }
                },
                modifier = Modifier
                    .fillMaxWidth(0.55f)
                    .fillMaxSize(0.425f)

            ) {
                Text(
                    text =
                    if (isCreating) stringResource(R.string.save_password)
                    else stringResource(R.string.login),
                    modifier = Modifier
                        //.fillMaxWidth(0.55f)
                        //.fillMaxSize(0.425f)
                        .padding(horizontal = 10.dp)
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                    fontSize = 27.sp,
                    lineHeight = 32.sp,
                )
            }
        }
        ////////////////////////////////////////////////////////
    }



}




@Preview
@Composable
fun LoginActivityPreview()
{
    LoginPage(
        onLogin = { },
        onFirst = { }
    )
}