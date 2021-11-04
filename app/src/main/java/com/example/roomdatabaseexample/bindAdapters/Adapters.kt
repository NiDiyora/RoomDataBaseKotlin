package com.example.roomdatabaseexample.bindAdapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.TextView
import androidx.databinding.BindingAdapter
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("setUserProfile")
fun CircleImageView.setimagebitmap(image: ByteArray?) {
    setImageBitmap(BitmapFactory.decodeByteArray(image, 0, image!!.size))
}

@BindingAdapter("setFirstName")
fun TextView.setTextMyProject(name: String) {
    setText(name)
}

