package com.example.pp2025_reasses.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import java.lang.reflect.Modifier

class SettingsActivityView {



    @Composable
    fun SettingPage()
    {

    }


    @Composable
    fun SectionBlock()
    {

    }



    @Composable
    fun SectionHeader(title: String, modifier: Modifier)
    {

    }

    @Composable
    fun OptionBlock(
        descriptionName: String,
        description: String,
        type: Int,
        condition: String?,
        modifier: androidx.compose.ui.Modifier
    )
    {
        Surface()
        {


            //Section Block
            Row(

            )
            {
                // Description Section
                Column {
                    Text(
                        text = descriptionName,
                        textAlign = TextAlign.Center,
                        modifier = androidx.compose.ui.Modifier
                            .weight(0.15f)
                    )

                    Text(
                        text = description,
                        textAlign = TextAlign.Start,
                        modifier = androidx.compose.ui.Modifier
                            .weight(0.85f)
                    )
                }


                //Setting Input Type
                when (type) {
                    //Input Box
                    1 ->
                    {
                        SettingInputField(condition)
                    }
                }
            }

            ////////////////////////////////////////////////////////
        }
    }

    @Composable
    fun SettingInputField(condition: String?)
    {
        var data by remember { mutableStateOf("") }

        TextField(
            value = data,
            onValueChange = {data = it},
            label =
            {
                if (condition != null)
                    condition
                else
                    ""
            }
        )
    }





    @Preview(showBackground = true)
    @Composable
    private fun SettingPagePreview()
    {



    }


    @Preview(showBackground = true)
    @Composable
    fun DropDownPreview()
    {
        SectionHeader(
            "ABC",
            modifier = Modifier
        )

    }

}