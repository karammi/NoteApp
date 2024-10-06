package com.asad.noteapp.core.data.date.localDataSource

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class DateLocalDataSourceImpl @Inject constructor() : DateLocalDataSource {

    override fun getFormattedDateTime(timeInMillis: Long): String {
        val sdf = SimpleDateFormat(DateConstants.DATE_TIME_FORMAT, Locale.getDefault())
        return sdf.format(Date(timeInMillis))
    }

    override fun getTimeDifferenceInMillis(timeInMillis: Long): Long {
        val systemTimeInMillis = System.currentTimeMillis()
        return timeInMillis - systemTimeInMillis
    }

    override fun getDateForDisplay(timeInMillis: Long): String {
        val sdf = SimpleDateFormat(DateConstants.REMINDER_DATE_TIME_FORMAT, Locale.getDefault())
        return sdf.format(timeInMillis)
    }

    override fun getDateForListOverview(timeInMillis: Long): String {
        val sdf = SimpleDateFormat(DateConstants.DATE_FORMAT, Locale.getDefault())
        return sdf.format(timeInMillis)
    }

    override fun getTimeInFormat(timeInMillis: Long): String {
        val sdf = SimpleDateFormat(DateConstants.TIME_FORMAT, Locale.getDefault())
        return sdf.format(timeInMillis)
    }

    override fun getFormattedDateRange(startTimeInMillis: Long, endTimeInMillis: Long?): String {
        val sdf = SimpleDateFormat(DateConstants.REMINDER_DATE_TIME_FORMAT, Locale.getDefault())
        val d1 = sdf.format(startTimeInMillis)
        return if (endTimeInMillis == null) {
            d1
        } else {
            d1 + DateConstants.SEPARATOR + "\n" + sdf.format(endTimeInMillis)
        }
    }
}