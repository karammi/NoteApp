package com.asad.noteapp.core.domain.date.repository

interface DateRepository {
    fun getFormattedDateTime(timeInMillis: Long): String

    fun getTimeDifferenceInMillis(timeInMillis: Long): Long

    fun getDateForDisplay(timeInMillis: Long): String

    fun getDateOverview(timeInMillis: Long): String

    fun getTimeInFormat(timeInMillis: Long): String

    fun getFormattedDateRange(startTimeInMillis: Long, endTimeInMillis: Long?): String
}