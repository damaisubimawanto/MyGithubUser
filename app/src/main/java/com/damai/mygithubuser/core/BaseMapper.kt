package com.damai.mygithubuser.core

/**
 * Created by damai.subimawanto on 2/18/2022.
 */
abstract class BaseMapper<in T, out R> {
    abstract fun map(value: T): R
}