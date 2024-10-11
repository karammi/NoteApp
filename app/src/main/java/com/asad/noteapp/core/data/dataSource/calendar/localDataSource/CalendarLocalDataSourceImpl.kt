package com.asad.noteapp.core.data.dataSource.calendar.localDataSource

import com.asad.noteapp.core.data.dataSource.calendar.localDataSource.util.DateConstants
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class CalendarLocalDataSourceImpl @Inject constructor() : CalendarLocalDataSource {

    override fun getFormattedDateTime(timeInMillis: Long): String {
        val sdf = SimpleDateFormat(DateConstants.DATE_TIME_FORMAT, Locale.getDefault())
        return sdf.format(Date(timeInMillis))
    }

    override fun getTimeInFormat(timeInMillis: Long): String {
        val sdf = SimpleDateFormat(DateConstants.TIME_FORMAT, Locale.getDefault())
        return sdf.format(timeInMillis)
    }

    override fun getTodayDateInMillis(): Long =
        Calendar.getInstance(Locale.getDefault()).timeInMillis

    override fun getHourAndMinute(timeInMillis: Long?): Pair<Int, Int> {
        val calendar = Calendar.getInstance(Locale.getDefault())
        timeInMillis?.let { calendar.timeInMillis = it }
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        return Pair(hour, minute)
    }

    override fun setDateTime(
        dateInMillis: Long?,
        hour: Int?,
        minute: Int?
    ): Long {
        val calendar = Calendar.getInstance(Locale.getDefault())
        dateInMillis?.let { calendar.timeInMillis = it }
        hour?.let { calendar.set(Calendar.HOUR_OF_DAY, it) }
        minute?.let { calendar.set(Calendar.MINUTE, it) }
        return calendar.timeInMillis
    }
}

