import android.app.DatePickerDialog
import android.content.Context
import com.asad.noteapp.core.data.dataSource.calendar.localDataSource.util.DateConstants
import java.text.SimpleDateFormat
import java.util.*

fun setDateTime(
    context: Context,
    doWork: (calendar: Calendar) -> Unit,
) {
    val mCalendar = Calendar.getInstance()
    val formatter = SimpleDateFormat(DateConstants.TIME_FORMAT, Locale.getDefault())
    var hour = formatter.format(mCalendar.time).substring(0, 2).trim().toInt()
    val min = formatter.format(mCalendar.time).substring(3, 5).trim().toInt()

    val isAm = formatter.format(mCalendar.time).substring(6).trim().lowercase()

    if (isAm == "pm_format")
        hour += 12

    val dateListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        mCalendar.set(Calendar.YEAR, year)
        mCalendar.set(Calendar.MONTH, monthOfYear)
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        doWork(mCalendar)
    }

    val datePickerDialog = DatePickerDialog(
        context,
        dateListener,
        mCalendar.get(Calendar.YEAR),
        mCalendar.get(Calendar.MONTH),
        mCalendar.get(Calendar.DAY_OF_MONTH)
    )
    datePickerDialog.show()
}


inline fun <reified M> setReminderWorkRequest(
    context: Context,
    title: String,
    data: M,
    calendar: Calendar,
) {
    val todayDateTime = Calendar.getInstance()
    val delayInSeconds =
        (calendar.timeInMillis / 1000L) - (todayDateTime.timeInMillis / 1000L)
//    createWorkRequest(
//        context,
//        data.id,
//        data.title,
//        null,
//        data.isDone,
//        data.startDate ?: System.currentTimeMillis(),
//        data.endDate,
//        delayInSeconds
//    )

}

