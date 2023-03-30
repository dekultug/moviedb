package com.example.themoviedatabase.common

import androidx.annotation.ColorRes
import com.example.themoviedatabase.R

const val LAYOUT_INVALID = -1

class StatusBar(@ColorRes var color: Int = R.color.app_primary, var isDarkText: Boolean = false)
