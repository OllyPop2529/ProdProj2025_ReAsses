package com.example.pp2025_reasses.data

import com.example.pp2025_reasses.R
import com.example.pp2025_reasses.model.OptFeature

class OptionData {
    fun loadGMaps(): List<OptFeature> {
        return listOf(
            OptFeature(R.string.gmT1, R.string.gmD1, 3),
            //OptFeature(R.string.gmT2, R.string.gmD2, 1),


            )
    }

    fun loadTheme(): List<OptFeature> {
        return listOf(
            OptFeature(R.string.themeT1, R.string.themeD1, 2),
            OptFeature(R.string.themeT2, R.string.themeD2, 2),
            OptFeature(R.string.themeT3, R.string.themeD3, 2),

            )
    }
}
