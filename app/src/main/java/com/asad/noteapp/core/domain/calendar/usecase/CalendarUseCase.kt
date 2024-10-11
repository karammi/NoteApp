package com.asad.noteapp.core.domain.calendar.usecase

import com.asad.noteapp.core.domain.calendar.repository.CalendarRepository
import javax.inject.Inject

class CalendarUseCase @Inject constructor(
    private val calendarRepository: CalendarRepository
) {
    fun getFormattedDateTime(timeInMillis: Long): String =
        calendarRepository.getFormattedDateTime(timeInMillis)

    fun getTimeInFormat(timeInMillis: Long): String =
        calendarRepository.getTimeInFormat(timeInMillis)

    fun getTodayDateInMillis(): Long = calendarRepository.getTodayDateInMillis()

    fun getHourAndMinute(timeInMillis: Long?): Pair<Int, Int> =
        calendarRepository.getHourAndMinute(timeInMillis)

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
