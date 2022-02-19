package com.damai.mygithubuser

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.damai.mygithubuser.data.util.DisplayHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.P])
class RepoLastUpdatedUnitTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val lastUpdatedTime = "2021-03-23T22:52:04Z"

    @Test
    fun normalScenario_isCorrect() {
        val result = DisplayHelper(context).generateRepoLastUpdated(time = lastUpdatedTime)
        println("zxczxc => $result")
        Assert.assertEquals("Updated March 23, 2021", result)
    }

    @Test
    fun emptyInput_isCorrect() {
        val result = DisplayHelper(context).generateRepoLastUpdated(time = "")
        println("zxczxc => $result")
        Assert.assertEquals("error", result)
    }

    @Test
    fun nullInput_isCorrect() {
        val result = DisplayHelper(context).generateRepoLastUpdated(time = null)
        println("zxczxc => $result")
        Assert.assertEquals("error", result)
    }

    @Test
    fun wrongFormat_isCorrect() {
        val result = DisplayHelper(context).generateRepoLastUpdated(time = "Wed, 4 Jul 2001 12:08:56")
        println("zxczxc => $result")
        Assert.assertEquals("error", result)
    }
}