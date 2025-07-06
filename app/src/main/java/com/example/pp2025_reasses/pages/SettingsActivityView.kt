package com.example.pp2025_reasses.pages


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pp2025_reasses.ATD_ViewModel
import com.example.pp2025_reasses.R
import com.example.pp2025_reasses.data.OptionData
import com.example.pp2025_reasses.model.OptFeature
import com.google.android.gms.maps.GoogleMap


@Composable
    fun SettingsPage(
    viewModel: ATD_ViewModel,
    onBack: () -> Unit
    ) {
        //Empty Spacing for Padding, Will Contain Icon
        Scaffold(
            topBar = {},
        )
        { it ->
            //LazyColumn acting as page scrolling
            Column(
                modifier = Modifier.padding(it)
            )
            {


                LazyColumn(
                    modifier = Modifier.weight(6.5f),
                    contentPadding = it,

                )
                {
                    item()
                    {
                        SectionBlock(
                            title = "Maps",
                            viewModel = viewModel,
                            optionList = OptionData().loadGMaps()
                        )
                    }

                    item()
                    {
                        SectionBlock(
                            title = "Application",
                            viewModel = viewModel,
                            optionList = OptionData().loadTheme()
                        )
                    }
                }

                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1.5f)
                        .padding(bottom = 15.dp)
                        .alpha(0.5f)
                        .background(Color.Red)
                        .padding(horizontal = 15.dp)
                        .fillMaxSize(),
                )
                {
                    Button(
                        onClick = { onBack() },
                        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                        elevation = ButtonDefaults.buttonElevation(5.dp,0.dp),
                        modifier = Modifier
                            .padding(all = 25.dp)
                            .fillMaxSize(),


                    ) {
                        Text(
                            text = "Return",
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

            }
        }

    }


    @Composable
    fun SectionBlock(
        optionList: List<OptFeature>,
        title: String,
        viewModel: ATD_ViewModel

    ) {
        // Keeps section Open/Close
        var isOpen by remember { mutableStateOf(true) }

        Column(
            modifier = Modifier.padding(bottom = 5.dp)
        )
        {
            SectionHeader(
                title = title,
                isOpen = isOpen,
                onSectionSelect = { isOpen = !isOpen },
                modifier = Modifier
            )
            if (isOpen) {
                for (option: OptFeature in optionList) {
                    OptionBlock(
                        descriptionName = stringResource(option.name),
                        description = stringResource(option.description),
                        type = option.type,
                        onMapTypeChange = viewModel::setMapType,
                        viewModel = viewModel

                    )
                }
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
        Surface()
        {

            Row(
                modifier = Modifier
                    .clickable { onSectionSelect() }
                    .background(Color.Red)
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 10.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Start
            )
            {
                //Title of the section
                Text(
                    text = title,
                    textAlign = TextAlign.Start
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
        onMapTypeChange: (Int) -> Unit,
        viewModel: ATD_ViewModel
    ) {
        //Surface - Added to apply color from theme (WIP)
        Surface(
            color = Color.LightGray,
            modifier = Modifier.padding(bottom = 2.dp),
            shadowElevation = 2.dp
        )
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
                    //Button
                    1 -> {}
                    //Toggle
                    2 -> {}
                    //Dropdown (For Map type)
                    3 -> {
                        MapTypeDropdown(
                            selectedType = viewModel.getMapType(),
                            modifier = modifier,
                            onMapTypeChange = onMapTypeChange
                        )

                    }
                }
            }

            ////////////////////////////////////////////////////////
        }
    }

//    @Composable
//    fun SettingInputField(condition: String?, modifier: Modifier) {
//        var data by remember { mutableStateOf("") }
//
//        TextField(
//            value = data,
//            onValueChange = { data = it },
//            label =
//            {
//                if (condition != null)
//                    condition
//                else
//                    ""
//            },
//            modifier = modifier
//                .padding(vertical = 10.dp, horizontal = 5.dp)
//        )
//    }



    @Composable
    fun MapTypeDropdown(
        selectedType: Int,
        onMapTypeChange: (Int) -> Unit,
        modifier: Modifier
    ) {
        val mapTypeOptions = mapOf(
            "Normal" to GoogleMap.MAP_TYPE_NORMAL,
            "Satellite" to GoogleMap.MAP_TYPE_SATELLITE,
            "Terrain" to GoogleMap.MAP_TYPE_TERRAIN,
            "Hybrid" to GoogleMap.MAP_TYPE_HYBRID
        )

        var expanded by remember { mutableStateOf(false) }
        val currentLabel = mapTypeOptions.entries.first { it.value == selectedType }.key

        Box(modifier = Modifier
            .padding(8.dp)
            .wrapContentSize(Alignment.TopStart)) {

            OutlinedButton(
                onClick = { expanded = true },
                modifier = modifier
            ) {
                Text("Map Type: $currentLabel")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                mapTypeOptions.forEach { (label, type) ->
                    DropdownMenuItem(
                        text = { Text(label) },
                        onClick = {
                            onMapTypeChange(type)
                            expanded = false
                        }
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun SettingPagePreview() {
    }


//    @Preview(showBackground = true)
//    @Composable
//    fun DropDownPreview() {
//
//        val viewModel : (Int) -> ();
//
//        SectionBlock(
//            title = "Maps",
//            viewModel = viewModel,
//            optionList = OptionData().loadGMaps()
//        )
//
//    }

