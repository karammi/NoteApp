package com.asad.noteapp.core.domain.calendar.repository

interface CalendarRepository {
    fun getFormattedDateTime(timeInMillis: Long): String

    fun getTimeDifferenceInMillis(timeInMillis: Long): Long

    fun getDateForDisplay(timeInMillis: Long): String

    fun getDateOverview(timeInMillis: Long): String

    fun getTimeInFormat(timeInMillis: Long): String

    fun getFormattedDateRange(startTimeInMillis: Long, endTimeInMillis: Long?): String

    fun getTodayDateInMillis(): Long

    fun setDateTime(dateInMillis: Long?, hour: Int?, minute: Int?): Long
}