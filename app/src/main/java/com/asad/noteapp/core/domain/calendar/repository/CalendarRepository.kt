package com.asad.noteapp.core.domain.calendar.repository

interface CalendarRepository {
    fun getFormattedDateTime(timeInMillis: Long): String

    fun getTimeInFormat(timeInMillis: Long): String

    fun getTodayDateInMillis(): Long

    fun getHourAndMinute(timeInMillis: Long?): Pair<Int, Int>

    fun setDateTime(dateInMillis: Long?, hour: Int?, minute: Int?): Long
}