package com.damai.mygithubuser.module

import com.damai.mygithubuser.data.util.DisplayHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
val helperModule = module {
    factory { DisplayHelper(androidContext()) }
}