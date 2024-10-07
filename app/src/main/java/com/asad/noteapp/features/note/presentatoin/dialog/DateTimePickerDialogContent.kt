package com.asad.noteapp.features.note.presentatoin.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DateTimePickerDialogContent(
    modifier: Modifier = Modifier,
    dateValue: String,
    timeValue: String,
    onShowDatePickerClicked: (Boolean) -> Unit,
    onShowTimePickerClicked: (Boolean) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Text(
            text = "Add reminder",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        DateTimePickerItem(
            modifier = Modifier,
            value = dateValue,
            onClick = { onShowDatePickerClicked(true) })

        HorizontalDivider(thickness = 1.dp)

        DateTimePickerItem(
            modifier = Modifier,
            value = timeValue,
            onClick = { onShowTimePickerClicked(true) })

        HorizontalDivider(thickness = 1.dp)

        DateTimePickerItem(
            modifier = Modifier,
            value = "Dose note repeat",
            onClick = { onShowTimePickerClicked(true) })
    }
}


@Composable
fun DateTimePickerItem(
    modifier: Modifier = Modifier,
    value: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .clickable { onClick() },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = value)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = ""
            )
        }
    }
}