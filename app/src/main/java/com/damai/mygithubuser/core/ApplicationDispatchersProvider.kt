package com.damai.mygithubuser.core

import kotlinx.coroutines.Dispatchers

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
class ApplicationDispatchersProvider : SchedulerProvider {
    override fun io() = Dispatchers.IO
    override fun ui() = Dispatchers.Main
    override fun default() = Dispatchers.Default
}