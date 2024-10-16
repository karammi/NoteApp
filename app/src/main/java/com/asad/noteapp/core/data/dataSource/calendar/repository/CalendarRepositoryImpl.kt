package com.asad.noteapp.core.data.dataSource.calendar.repository

import com.asad.noteapp.core.data.dataSource.calendar.localDataSource.CalendarLocalDataSource
import com.asad.noteapp.core.domain.calendar.repository.CalendarRepository
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    private val calendarLocalDataSource: CalendarLocalDataSource
) : CalendarRepository {
    override fun getFormattedDateTime(timeInMillis: Long): String =
        calendarLocalDataSource.getFormattedDateTime(timeInMillis)

    override fun getTimeInFormat(timeInMillis: Long): String =
        calendarLocalDataSource.getTimeInFormat(timeInMillis)

    override fun getTodayDateInMillis(): Long = calendarLocalDataSource.getTodayDateInMillis()

    override fun getHourAndMinute(timeInMillis: Long?): Pair<Int, Int> =
        calendarLocalDataSource.getHourAndMinute(timeInMillis)

    override fun setDateTime(
        dateInMillis: Long?,
        hour: Int?,
        minute: Int?
    ): Long = calendarLocalDataSource.setDateTime(
        dateInMillis = dateInMillis,
        hour = hour,
        minute = minute
    )

}
