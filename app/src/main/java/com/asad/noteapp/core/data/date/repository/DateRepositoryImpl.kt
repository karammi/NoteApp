package com.asad.noteapp.core.data.date.repository

import com.asad.noteapp.core.data.date.localDataSource.DateLocalDataSource
import com.asad.noteapp.core.domain.date.repository.DateRepository
import javax.inject.Inject

class DateRepositoryImpl @Inject constructor(
    private val dateLocalDataSource: DateLocalDataSource
) : DateRepository {
    override fun getFormattedDateTime(timeInMillis: Long): String =
        dateLocalDataSource.getFormattedDateTime(timeInMillis)

    override fun getTimeDifferenceInMillis(timeInMillis: Long): Long =
        dateLocalDataSource.getTimeDifferenceInMillis(timeInMillis)

    override fun getDateForDisplay(timeInMillis: Long): String =
        dateLocalDataSource.getDateForDisplay(timeInMillis)

    override fun getDateOverview(timeInMillis: Long): String =
        dateLocalDataSource.getDateForListOverview(timeInMillis)

    override fun getTimeInFormat(timeInMillis: Long): String =
        dateLocalDataSource.getTimeInFormat(timeInMillis)

    override fun getFormattedDateRange(startTimeInMillis: Long, endTimeInMillis: Long?): String =
        dateLocalDataSource.getFormattedDateRange(startTimeInMillis, endTimeInMillis)
}
