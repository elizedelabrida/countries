package com.elize.countries.extension

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(url: String?, progressDrawable: CircularProgressDrawable) {
    val options = getDefaultRequestOptions(progressDrawable)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

private fun getDefaultRequestOptions(progressDrawable: CircularProgressDrawable): RequestOptions {
    return RequestOptions()
        .placeholder(progressDrawable)
        .error(null)
}
