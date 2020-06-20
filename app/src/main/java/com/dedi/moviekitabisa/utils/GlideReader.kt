package com.dedi.moviekitabisa.utils


import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.dedi.moviekitabisa.R


internal fun ImageView.imageLoad(url: String) {
    Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
        )
        .load("https://image.tmdb.org/t/p/w500$url")
        .dontAnimate()
        .centerCrop()
        .into(this)
}