package com.example.reposearchapp.util

import androidx.databinding.BindingAdapter
import kotlin.math.ln
import kotlin.math.pow

fun Int.formatNumber(): String {
    if (this < 1000) return "$this"
    val exp = (ln(this.toDouble()) / ln(1000.0)).toInt()
    return String.format("%.1f %c", this / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1])
}