package com.asad.noteapp.core.data.dataSource.calendar.localDataSource

interface CalendarLocalDataSource {
    fun getFormattedDateTime(timeInMillis: Long): String

    fun getTimeDifferenceInMillis(timeInMillis: Long): Long

    fun getDateForDisplay(timeInMillis: Long): String

    fun getDateForListOverview(timeInMillis: Long): String

    fun getTimeInFormat(timeInMillis: Long): String

    fun getFormattedDateRange(startTimeInMillis: Long, endTimeInMillis: Long?): String

    fun getTodayDateInMillis(): Long
}