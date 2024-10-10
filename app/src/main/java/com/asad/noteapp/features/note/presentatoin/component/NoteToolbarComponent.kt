package com.asad.noteapp.features.note.presentatoin.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asad.noteapp.R

@Composable
fun RowScope.NoteToolbarComponent(
    onBackClicked: () -> Unit,
    onSetReminderClicked: (Boolean) -> Unit
) {
    Spacer(modifier = Modifier.width(8.dp))
    Image(
        modifier = Modifier
            .clip(shape = CircleShape)
            .size(48.dp)
            .clickable { onBackClicked() },
        painter = painterResource(R.drawable.ic_back),
        contentDescription = stringResource(R.string.layout_content_desc)
    )

    Spacer(modifier = Modifier.weight(1f))

    Image(
        modifier = Modifier
            .size(48.dp)
            .clickable { onSetReminderClicked(true) },
        painter = painterResource(R.drawable.ic_alarm),
        contentDescription = stringResource(R.string.reminder_content_desc)
    )
    Spacer(modifier = Modifier.width(8.dp))
    Image(
        modifier = Modifier
            .size(48.dp)
            .clickable { },
        painter = painterResource(R.drawable.ic_inbox),
        contentDescription = stringResource(R.string.inbox_content_desc)
    )
    Spacer(modifier = Modifier.width(8.dp))
}

@Preview(showSystemUi = true)
@Composable
fun NoteToolbarComponentPreview() {
    Row {
        NoteToolbarComponent(
            onBackClicked = {},
            onSetReminderClicked = {}
        )
    }
}