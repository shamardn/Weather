package com.shamardn.weather.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.shamardn.weather.Constants

@BindingAdapter(value = ["app:imageUrl"])
fun setImageFromUrl(view: ImageView, url: String?) {
    val defaultIcon = Constants.URL_IMAGE + url + Constants.IMAGE_EXTENSION
    Glide.with(view).load(defaultIcon).into(view)
}