package com.asad.noteapp.core.data.dataSource.calendar.localDataSource

interface CalendarLocalDataSource {
    fun getFormattedDateTime(timeInMillis: Long): String

    fun getTimeInFormat(timeInMillis: Long): String

    fun getTodayDateInMillis(): Long

    fun getHourAndMinute(timeInMillis: Long?): Pair<Int, Int>

    fun setDateTime(dateInMillis: Long?, hour: Int?, minute: Int?): Long

}