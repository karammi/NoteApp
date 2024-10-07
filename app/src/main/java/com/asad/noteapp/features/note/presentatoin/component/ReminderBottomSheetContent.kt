package com.asad.noteapp.features.note.presentatoin.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            .padding(
                horizontal = 24.dp,
                vertical = 8.dp
            ),
        title = "Later Today",
        detail = "6:30 PM",
        leadingResIcon = R.drawable.ic_clock,
        showDivider = true,
        onItemClicked = onReminderItemClicked
    )

    ReminderBottomSheetItemComponent(
        modifier = Modifier
            .height(50.dp)
            .padding(
                horizontal = 24.dp,
                vertical = 8.dp
            ),
        title = "Tomorrow Morning",
        detail = "6:30 PM",
        leadingResIcon = R.drawable.ic_clock,
        showDivider = true,
        onItemClicked = onReminderItemClicked
    )

    ReminderBottomSheetItemComponent(
        modifier = Modifier
            .height(50.dp)
            .padding(
                horizontal = 24.dp,
                vertical = 8.dp
            ),
        title = "Home",
        detail = "Tehran",
        leadingResIcon = R.drawable.ic_clock,
        showDivider = true,
        onItemClicked = onReminderItemClicked
    )

    ReminderBottomSheetItemComponent(
        modifier = Modifier
            .height(50.dp)
            .padding(
                horizontal = 24.dp,
                vertical = 8.dp
            ),
        title = "Pick a Date",
        detail = null,
        leadingResIcon = R.drawable.ic_calendar,
        showDivider = false,
        onItemClicked = onPickDateClicked,
        trailingResIcon = R.drawable.ic_add
    )

    Spacer(modifier = Modifier.height(32.dp))

}

