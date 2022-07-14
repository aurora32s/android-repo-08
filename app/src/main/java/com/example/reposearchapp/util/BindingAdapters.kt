package com.example.reposearchapp.util

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("circleImageUrl")
fun setCircleImageFromImageUrl(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView.context)
            .load(imageUrl)
            .circleCrop()
            .into(imageView)
    }
}

@SuppressLint("UseCompatTextViewDrawableApis")
@BindingAdapter("tvDrawableTint")
fun setTextViewDrawableTint(textView: TextView, color: String?) {
    val defaultColor = "#ffffff"
    val c = color ?: defaultColor

    val colorCode = try {
        Color.parseColor(c)
    } catch (e: IllegalArgumentException) {
        Color.parseColor(defaultColor)
    }

    textView.compoundDrawableTintList = ColorStateList.valueOf(colorCode)
}