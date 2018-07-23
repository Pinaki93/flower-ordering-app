package com.pinaki.example.rxjavatest

import android.app.Activity
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ImageUtil {
    companion object {
        fun loadImage(url: String, iv: ImageView, fragment: Fragment, @DrawableRes drawableRes: Int) {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(drawableRes)
            requestOptions.centerCrop()

            Glide.with(fragment)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(url)
                    .into(iv)
        }

        fun loadImage(url: String, iv: ImageView, activity: Activity, @DrawableRes drawableRes: Int) {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(drawableRes)
            requestOptions.centerCrop()

            Glide.with(activity)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(url)
                    .into(iv)
        }
    }
}