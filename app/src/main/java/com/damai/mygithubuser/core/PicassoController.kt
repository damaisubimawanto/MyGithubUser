package com.damai.mygithubuser.core

import android.widget.ImageView
import com.damai.mygithubuser.core.customview.DefaultCircleTransform
import com.squareup.picasso.Picasso

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
object PicassoController {

    fun loadImageCircle(
        url: String?,
        imageView: ImageView
    ) {
        Picasso.get().load(url).fit().centerCrop()
            .transform(DefaultCircleTransform())
            .into(imageView)
    }
}