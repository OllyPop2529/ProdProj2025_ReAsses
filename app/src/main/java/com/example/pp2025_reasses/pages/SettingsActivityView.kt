package com.example.pp2025_reasses.pages


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


    @Composable
    fun SettingsPage() {
        //Empty Spacing for Padding, Will Contain Icon
        Scaffold(
            topBar = {},
        )
        { it ->
            //LazyColumn acting as page scrolling
            LazyColumn(contentPadding = it)
            {
                items(5)
                {
                    SectionBlock()
                }

            }
        }

    }


    @Composable
    fun SectionBlock() {
        // Keeps section Open/Close
        var isOpen by remember { mutableStateOf(true) }

        Column()
        {
            SectionHeader(
                title = "Bluetooth Test",
                isOpen = isOpen,
                onSectionSelect = { isOpen = !isOpen },
                modifier = Modifier
            )
            if (isOpen) {
                OptionBlock("Connectivity", "ABC", 1, null)
                OptionBlock("Speedivity", "24124", 1, null)
                OptionBlock("Magical Capability", "100%", 1, null)
            }
        }
    }


    @Composable
    fun SectionHeader(
        title: String,
        isOpen: Boolean,
        onSectionSelect: () -> Unit,
        modifier: Modifier

    ) {
        //Surface - Added to apply color from theme (WIP)
        Surface(

        )
        {

            Row(
                modifier = Modifier
                    .clickable { onSectionSelect() }
                    .fillMaxWidth(0.3f)
                    .padding(vertical = 10.dp, horizontal = 10.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Start
            )
            {
                //Title of the section
                Text(
                    text = title,
                )


            }


            ////////////////////////////////////////////////////////
        }

    }

    @Composable
    fun OptionBlock(
        descriptionName: String,
        description: String,
        type: Int,
        condition: String?,
    ) {
        //Surface - Added to apply color from theme (WIP)
        Surface()
        {


            //Section Block
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                // Description Section
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.7f)
                )
                {
                    //Title of Option
                    Text(
                        text = descriptionName,
                        textAlign = TextAlign.Start,
                        modifier = Modifier

                    )

                    //Description of Option
                    Text(
                        text = description,
                        textAlign = TextAlign.Start,
                        modifier = Modifier

                    )
                }

                val modifier: Modifier = Modifier.weight(0.3f)
                //Setting Input Type
                when (type) {
                    //Input Box
                    1 -> {
                        SettingInputField(condition, modifier)
                    }
                }
            }

            ////////////////////////////////////////////////////////
        }
    }

    @Composable
    fun SettingInputField(condition: String?, modifier: Modifier) {
        var data by remember { mutableStateOf("") }

        TextField(
            value = data,
            onValueChange = { data = it },
            label =
            {
                if (condition != null)
                    condition
                else
                    ""
            },
            modifier = modifier
                .padding(vertical = 10.dp, horizontal = 5.dp)
        )
    }


    @Preview(showBackground = true)
    @Composable
    private fun SettingPagePreview() {


    }


    @Preview(showBackground = true)
    @Composable
    fun DropDownPreview() {
        SectionBlock()

    }

