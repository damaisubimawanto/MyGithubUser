package com.damai.mygithubuser.data.util

import android.content.Context
import com.damai.mygithubuser.R

/**
 * Created by damai.subimawanto on 2/19/2022.
 */
class DisplayHelper(
    private val context: Context
) {

    fun generateDisplayName(displayName: String?): String {
        return if (displayName.isNullOrBlank()) {
            context.getString(R.string.text_empty_name)
        } else {
            displayName
        }
    }

    fun generateNickname(nickname: String?): String? {
        return if (nickname.isNullOrBlank()) {
            null
        } else {
            "@$nickname"
        }
    }

    fun generateJobPosition(jobPosition: String?): String {
        return if (jobPosition.isNullOrBlank()) {
            context.getString(R.string.text_empty_job_position)
        } else {
            jobPosition
        }
    }

    fun generateLocation(location: String?): String {
        return if (location.isNullOrBlank()) {
            context.getString(R.string.text_empty_location)
        } else {
            location
        }
    }

    fun generateEmail(email: String?): String {
        return if (email.isNullOrBlank()) {
            "-"
        } else {
            email
        }
    }
}