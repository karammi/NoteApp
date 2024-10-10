package com.asad.noteapp.features.note.presentatoin.sheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.asad.noteapp.R

@Composable
fun ReminderBottomSheetContent(
    onReminderItemClicked: () -> Unit,
    onPickDateClicked: () -> Unit
) {
    ReminderBottomSheetItemComponent(
        modifier = Modifier
            .height(50.dp)
            .testTag(tag = stringResource(R.string.later_today_test_tag))
            .padding(
                horizontal = 24.dp,
                vertical = 8.dp
            ),
        title = stringResource(R.string.later_today_label),
        detail = "6:30 PM",
        leadingResIcon = R.drawable.ic_clock,
        showDivider = true,
        onItemClicked = onReminderItemClicked
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
        detail = "6:30 PM",
        leadingResIcon = R.drawable.ic_clock,
        showDivider = true,
        onItemClicked = onReminderItemClicked
    )

    ReminderBottomSheetItemComponent(
        modifier = Modifier
            .height(50.dp)
            .testTag(tag = stringResource(R.string.home_test_tag))
            .padding(
                horizontal = 24.dp,
                vertical = 8.dp
            ),
        title = stringResource(R.string.home_label),
        detail = "Tehran",
        leadingResIcon = R.drawable.ic_clock,
        showDivider = true,
        onItemClicked = onReminderItemClicked
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

