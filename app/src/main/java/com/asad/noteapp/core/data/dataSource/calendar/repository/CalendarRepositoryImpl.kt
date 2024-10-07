package com.asad.noteapp.core.data.dataSource.calendar.repository

import com.asad.noteapp.core.data.dataSource.calendar.localDataSource.CalendarLocalDataSource
import com.asad.noteapp.core.domain.calendar.repository.CalendarRepository
import javax.inject.Inject

class CalendarRepositoryImpl @Inject constructor(
    private val calendarLocalDataSource: CalendarLocalDataSource
) : CalendarRepository {
    override fun getFormattedDateTime(timeInMillis: Long): String =
        calendarLocalDataSource.getFormattedDateTime(timeInMillis)

    override fun getTimeDifferenceInMillis(timeInMillis: Long): Long =
        calendarLocalDataSource.getTimeDifferenceInMillis(timeInMillis)

    override fun getDateForDisplay(timeInMillis: Long): String =
        calendarLocalDataSource.getDateForDisplay(timeInMillis)

    override fun getDateOverview(timeInMillis: Long): String =
        calendarLocalDataSource.getDateForListOverview(timeInMillis)

    override fun getTimeInFormat(timeInMillis: Long): String =
        calendarLocalDataSource.getTimeInFormat(timeInMillis)

    override fun getFormattedDateRange(startTimeInMillis: Long, endTimeInMillis: Long?): String =
        calendarLocalDataSource.getFormattedDateRange(startTimeInMillis, endTimeInMillis)

    override fun getTodayDateInMillis(): Long = calendarLocalDataSource.getTodayDateInMillis()
}
