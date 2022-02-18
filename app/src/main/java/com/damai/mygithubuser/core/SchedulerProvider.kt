package com.damai.mygithubuser.core

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
interface SchedulerProvider {
    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher
    fun default(): CoroutineDispatcher
}