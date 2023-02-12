package com.shamardn.weather.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["app:imageUrl"])
fun setImageFromUrl(view: ImageView, url: String?) {
    val defaultIcon = Constants.URL_IMAGE + url + Constants.IMAGE_EXTENSION
    Glide.with(view).load(defaultIcon).into(view)
}

@BindingAdapter(value = ["app:showIfSuccess"])
fun showIfSuccess(view: View, state: Boolean) {
    if (state) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showIfLoading"])
fun showIfLoading(view: View, state: Boolean) {
    if (state) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showIfError"])
fun showIfError(view: View, state: Boolean) {
    if (state) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("android:visibility")
fun View.setVisibility(visible: Boolean) {
    visibility = if (visible) {
        View.VISIBLE
    } else {
        View.GONE
    }
}