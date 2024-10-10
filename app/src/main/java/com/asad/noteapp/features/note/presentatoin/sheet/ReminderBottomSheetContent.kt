package com.asad.noteapp.features.note.presentatoin.sheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.asad.noteapp.R
import com.asad.noteapp.core.data.dataSource.calendar.localDataSource.util.DateConstants
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun ReminderBottomSheetContent(
    onReminderItemClicked: (Long) -> Unit,
    onPickDateClicked: () -> Unit
) {
    val laterTimeValue = remember(key1 = Unit) {
        val calendar = Calendar.getInstance(Locale.getDefault())
        calendar.add(Calendar.HOUR, 1)
        val sdf = SimpleDateFormat(DateConstants.TIME_FORMAT, Locale.getDefault())
        sdf.format(calendar.timeInMillis) to calendar.timeInMillis
    }

    val tomorrowTimeValue = remember(key1 = Unit) {
        val calendar = Calendar.getInstance(Locale.getDefault())
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        val sdf = SimpleDateFormat(DateConstants.TIME_FORMAT, Locale.getDefault())
        sdf.format(calendar.timeInMillis) to calendar.timeInMillis
    }

    val nextWeekTimeValue = remember(key1 = Unit) {
        val calendar = Calendar.getInstance(Locale.getDefault())
        calendar.add(Calendar.WEEK_OF_YEAR, 1)
        val sdf = SimpleDateFormat(DateConstants.TIME_FORMAT, Locale.getDefault())
        sdf.format(calendar.timeInMillis) to calendar.timeInMillis
    }


    ReminderBottomSheetItemComponent(
        modifier = Modifier
            .height(50.dp)
            .testTag(tag = stringResource(R.string.later_today_test_tag))
            .padding(
                horizontal = 24.dp,
                vertical = 8.dp
            ),
        title = stringResource(R.string.later_today_label),
        detail = laterTimeValue.first,
        leadingResIcon = R.drawable.ic_clock,
        showDivider = true,
        onItemClicked = { onReminderItemClicked(laterTimeValue.second) }
    )

    ReminderBottomSheetItemComponent(
        modifier = Modifier
            .height(50.dp)
            .testTag(tag = stringResource(R.string.tomorrow_morning_test_tag))
            .padding(
                horizontal = 24.dp,
                vertical = 8.dp
            ),
        title = stringResource(R.string.tomorrow_morning),
        detail = tomorrowTimeValue.first,
        leadingResIcon = R.drawable.ic_clock,
        showDivider = true,
        onItemClicked = { onReminderItemClicked(tomorrowTimeValue.second) }
    )

    ReminderBottomSheetItemComponent(
        modifier = Modifier
            .height(50.dp)
            .testTag(tag = stringResource(R.string.next_week_test_tag))
            .padding(
                horizontal = 24.dp,
                vertical = 8.dp
            ),
        title = stringResource(R.string.next_week_label),
        detail = nextWeekTimeValue.first,
        leadingResIcon = R.drawable.ic_clock,
        showDivider = true,
        onItemClicked = { onReminderItemClicked(nextWeekTimeValue.second) }
    )

    ReminderBottomSheetItemComponent(
        modifier = Modifier
            .height(50.dp)
            .testTag(tag = stringResource(R.string.pick_a_date_test_tag))
            .padding(
                horizontal = 24.dp,
                vertical = 8.dp
            ),
        title = stringResource(R.string.pick_a_date_label),
        detail = null,
        leadingResIcon = R.drawable.ic_calendar,
        showDivider = false,
        onItemClicked = onPickDateClicked,
        trailingResIcon = R.drawable.ic_add
    )

    Spacer(modifier = Modifier.height(32.dp))

}

