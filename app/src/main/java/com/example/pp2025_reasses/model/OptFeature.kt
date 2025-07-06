package com.example.pp2025_reasses.model

import androidx.annotation.StringRes

data class OptFeature(
    @StringRes val name: Int,
    @StringRes val description: Int,
    val type: Int,

    )