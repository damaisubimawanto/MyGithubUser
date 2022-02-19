package com.damai.mygithubuser.data.util

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
import android.text.TextUtils
import android.text.format.DateUtils
import android.text.format.DateUtils.FORMAT_ABBREV_RELATIVE
import android.text.style.StyleSpan
import com.damai.mygithubuser.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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

    fun generateFollowers(followersNumber: Int): CharSequence {
        val followersDigit = when {
            followersNumber < 1_000 -> {
                "$followersNumber"
            }
            followersNumber < 1_000_000 -> {
                val digit = followersNumber / 1_000
                "${digit}K"
            }
            else -> {
                val digit = followersNumber / 1_000_000
                "${digit}M"
            }
        }
        val spanDigit = SpannableString(followersDigit)
        val spanFollowers = SpannableString(context.getString(R.string.text_user_followers))

        spanDigit.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            spanDigit.length,
            SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spanFollowers.setSpan(
            StyleSpan(Typeface.NORMAL),
            0,
            spanFollowers.length,
            SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return TextUtils.concat(spanDigit, " ", spanFollowers)
    }

    fun generateFollowing(followingNumber: Int): CharSequence {
        val followersDigit = when {
            followingNumber < 1_000 -> {
                "$followingNumber"
            }
            followingNumber < 1_000_000 -> {
                val digit = followingNumber / 1_000
                "${digit}K"
            }
            else -> {
                val digit = followingNumber / 1_000_000
                "${digit}M"
            }
        }
        val spanDigit = SpannableString(followersDigit)
        val spanFollowers = SpannableString(context.getString(R.string.text_user_following))

        spanDigit.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            spanDigit.length,
            SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spanFollowers.setSpan(
            StyleSpan(Typeface.NORMAL),
            0,
            spanFollowers.length,
            SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return TextUtils.concat(spanDigit, " ", spanFollowers)
    }

    /**
     * Example of time = 2021-03-23T22:52:04Z
     */
    fun generateRepoLastUpdated(time: String?): String {
        if (time.isNullOrBlank()) {
            return "error"
        }
        val formatting = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val date = try {
            formatting.parse(time)
        } catch (e: ParseException) {
            null
        } catch (e: RuntimeException) {
            null
        } catch (e: Exception) {
            null
        }
        date?.time?.let { timestamp ->
            val agoText = DateUtils.getRelativeTimeSpanString(
                timestamp,
                Date().time,
                DateUtils.MINUTE_IN_MILLIS,
                FORMAT_ABBREV_RELATIVE
            ).toString()
            /*return "Updated $agoText"*/ // it's for test unit
            return "${context.getString(R.string.text_updated)} $agoText"
        }
        return "error"
    }
}