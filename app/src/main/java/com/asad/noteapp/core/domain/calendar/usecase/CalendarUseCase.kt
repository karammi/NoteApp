package com.asad.noteapp.core.domain.calendar.usecase

import com.asad.noteapp.core.domain.calendar.repository.CalendarRepository
import javax.inject.Inject

class CalendarUseCase @Inject constructor(
    private val calendarRepository: CalendarRepository
) {
    fun getFormattedDateTime(timeInMillis: Long): String =
        calendarRepository.getFormattedDateTime(timeInMillis)

    fun getTimeDifferenceInMillis(timeInMillis: Long): Long =
        calendarRepository.getTimeDifferenceInMillis(timeInMillis)

    fun getDateForDisplay(timeInMillis: Long): String =
        calendarRepository.getDateForDisplay(timeInMillis)

    fun getDateOverview(timeInMillis: Long): String =
        calendarRepository.getDateOverview(timeInMillis)

    fun getTimeInFormat(timeInMillis: Long): String =
        calendarRepository.getTimeInFormat(timeInMillis)

    fun getFormattedDateRange(startTimeInMillis: Long, endTimeInMillis: Long?): String =
        calendarRepository.getFormattedDateRange(startTimeInMillis, endTimeInMillis)

    fun getTodayDateInMillis(): Long = calendarRepository.getTodayDateInMillis()

    fun setDateTime(
        dateInMillis: Long?,
        hour: Int? = null,
        minute: Int? = null
    ): Long = calendarRepository.setDateTime(
        dateInMillis = dateInMillis,
        hour = hour,
        minute = minute
    )
}
