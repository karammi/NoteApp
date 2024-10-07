package com.asad.noteapp.core.data.dataSource.calendar.localDataSource

import android.util.Log
import com.asad.noteapp.core.data.dataSource.calendar.localDataSource.util.DateConstants
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

private const val TAG = "CalendarLocal"

class CalendarLocalDataSourceImpl @Inject constructor() : CalendarLocalDataSource {

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

    override fun getTodayDateInMillis(): Long {
        val time = Calendar.getInstance(Locale.getDefault()).timeInMillis

        Log.d(TAG, "getTodayDate: ${getFormattedDateTime(time)}") //Wed, 09 October

        Log.d(TAG, "getTodayDate: ${getDateForDisplay(time)}") //09 Oct, 24 05:54 PM

        Log.d(TAG, "getTodayDate: ${getTimeInFormat(time)}") // 05:54 PM

        Log.d(TAG, "getTodayDate: ${getDateForDisplay(time)}") //09 Oct, 24 05:54 PM

        return time
    }
}

