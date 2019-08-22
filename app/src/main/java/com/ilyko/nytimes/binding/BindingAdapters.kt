package com.ilyko.nytimes.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("movieImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
            .load(imageUrl)
            .into(view)
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter(value = ["dateValue","inPattern", "outPattern"])
fun formatDateText(textView: TextView, dateValue: String, inPattern: String, outPattern: String) {
    val inFormat = SimpleDateFormat(inPattern, Locale.getDefault())
    val outFormat = SimpleDateFormat(outPattern, Locale.getDefault())
    val date = inFormat.parse(dateValue)
    textView.text = outFormat.format(date)
}