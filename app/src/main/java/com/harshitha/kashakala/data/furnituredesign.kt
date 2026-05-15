package com.harshitha.kashakala.data

import androidx.annotation.DrawableRes

data class FurnitureDesign(
    val id: Int,
    val name: String,
    @DrawableRes val imageRes: Int
)
