package com.example.productionproject

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pp2025_reasses.R
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class LoginActivityView {

}

@Composable
fun LoginPage(onAuthentication: () -> Unit)
{
    val modifier : Modifier = Modifier
        .padding(horizontal = 15.dp)
        .fillMaxSize()
        .alpha(0.5f)

    val context = LocalContext.current
    val prefs = remember { getEncryptedPrefs(context) }
    var storedPassword = prefs.getString("password", null)
    var inputPassword by remember { mutableStateOf("") }
    var errorText by remember { mutableStateOf("") }

    val isCreating = storedPassword == null

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
                text = if (isCreating) "Create a password you'll remember!" else "Enter Password",
                textAlign = TextAlign.Justify,
                fontSize = 25.sp,
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
            Button(onClick = {
                if (isCreating) {
                    prefs.edit().putString("password", inputPassword).apply()
                    onAuthentication()
                } else {
                    if (inputPassword == storedPassword) {
                        onAuthentication()
                    } else {
                        errorText = "Incorrect password"
                    }
                }
            }) {
                Text(
                    text = if (isCreating) "Save Password" else "Login",
                    modifier = Modifier
                        .fillMaxWidth(0.55f)
                        .fillMaxSize(0.425f)
                        .padding()
                )
            }
        }
        ////////////////////////////////////////////////////////
    }



}

fun getEncryptedPrefs(context: Context): SharedPreferences {
    val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    return EncryptedSharedPreferences.create(
        context,
        "secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}



@Preview
@Composable
fun LoginActivityPreview()
{
    LoginPage({})
}