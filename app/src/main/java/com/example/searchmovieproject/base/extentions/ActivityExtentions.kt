package com.example.searchmovieproject.base.extentions

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.transition.Transition
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.example.searchmovieproject.R
import com.squareup.picasso.Picasso

// com.squareup.picasso.Picasso

fun Activity.showToast (message : String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT) .show()


}

fun setPicasso (view: ImageView, path : String) {
    Picasso.get().load("https://image.tmdb.org/t/p/original/"+path)
        .centerCrop().into(view)

}









fun Context.setGlide (url : String , view : ImageView ){
    Glide.with(this)
        .load("https://image.tmdb.org/t/p/original/"+url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.images_place_holder)
        .fitCenter()
        .into(view)

}

fun glideToBitmap(context:Context,path:String) {

    Glide.with(context)
        .asBitmap()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .load(path)
        .into(object : CustomTarget<Bitmap>() {

            override fun onResourceReady(
                resource: Bitmap,
                transition: com.bumptech.glide.request.transition.Transition<in Bitmap>?
            ) {
                TODO("Not yet implemented")
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                TODO("Not yet implemented")
            }
        })

}


//fun Context.isInternetAvailable(): Boolean {
//    val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    val activeNetworkInfo = manager.activeNetworkInfo
//    return activeNetworkInfo != null && activeNetworkInfo.isConnected
//}

    fun Activity.imageOverlay(firstImage: Bitmap, secondImage: Bitmap): Bitmap {
        val result = Bitmap.createBitmap(firstImage.width, firstImage.height, firstImage.config)
        val canvas = Canvas(result)
        canvas.drawBitmap(firstImage, 0f, 0f, null)
        canvas.drawBitmap(secondImage, 10f, 10f, null)
        return result
    }

