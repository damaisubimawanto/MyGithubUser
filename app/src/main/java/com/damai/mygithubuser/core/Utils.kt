package com.damai.mygithubuser.core

import android.content.Context
import android.widget.Toast

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}