package com.asad.noteapp.core.domain.date.usecase

import com.asad.noteapp.core.domain.date.repository.DateRepository
import javax.inject.Inject

class DateUseCase @Inject constructor(
    private val dateRepository: DateRepository
) {
    fun getFormattedDateTime(timeInMillis: Long): String =
        dateRepository.getFormattedDateTime(timeInMillis)

    fun getTimeDifferenceInMillis(timeInMillis: Long): Long =
        dateRepository.getTimeDifferenceInMillis(timeInMillis)

    fun getDateForDisplay(timeInMillis: Long): String =
        dateRepository.getDateForDisplay(timeInMillis)

    fun getDateOverview(timeInMillis: Long): String =
        dateRepository.getDateOverview(timeInMillis)

    fun getTimeInFormat(timeInMillis: Long): String =
        dateRepository.getTimeInFormat(timeInMillis)

    fun getFormattedDateRange(startTimeInMillis: Long, endTimeInMillis: Long?): String =
        dateRepository.getFormattedDateRange(startTimeInMillis, endTimeInMillis)

}