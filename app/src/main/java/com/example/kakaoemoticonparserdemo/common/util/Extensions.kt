package com.example.kakaoemoticonparserdemo.common.util

import com.example.kakaoemoticonparserdemo.common.theme.*

fun AppThemeState.parseColor() = when (pallet) {
    ColorPallet.GREEN -> green700
    ColorPallet.BLUE -> blue700
    ColorPallet.ORANGE -> orange700
    ColorPallet.PURPLE -> purple700
}
