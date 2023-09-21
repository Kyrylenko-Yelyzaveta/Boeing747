package com.flyinthesky.boeing747.dto

import androidx.annotation.DrawableRes
import com.flyinthesky.boeing747.R
import java.io.Serializable


data class BoeingItem(
    @DrawableRes val imageView: Int = R.drawable.boeing_747,
    val mainText: String = "Boeing 747",
    val defaultText: String =  "1970"
): Serializable
