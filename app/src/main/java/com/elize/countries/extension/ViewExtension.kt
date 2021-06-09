package com.elize.countries.extension

import android.view.View

fun View.changeVisibility(isVisible : Boolean) {
    visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}