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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asad.noteapp.R

@Composable
fun RowScope.NoteToolbarComponent(
    onBackClicked: () -> Unit,
    onReminderClicked: () -> Unit
) {
    Spacer(modifier = Modifier.width(8.dp))
    Image(
        modifier = Modifier
            .clip(shape = CircleShape)
            .size(48.dp)
            .clickable { onBackClicked() },
        painter = painterResource(R.drawable.ic_back),
        contentDescription = "layout"
    )

    Spacer(modifier = Modifier.weight(1f))

    Image(
        modifier = Modifier
            .size(48.dp)
            .clickable { onReminderClicked() },
        painter = painterResource(R.drawable.ic_alarm),
        contentDescription = "reminder"
    )
    Spacer(modifier = Modifier.width(8.dp))
    Image(
        modifier = Modifier
            .size(48.dp)
            .clickable { },
        painter = painterResource(R.drawable.ic_inbox),
        contentDescription = "inbox"
    )
    Spacer(modifier = Modifier.width(8.dp))
}

@Preview(showSystemUi = true)
@Composable
fun NoteToolbarComponentPreview(modifier: Modifier = Modifier) {
    Row {
        NoteToolbarComponent(
            onBackClicked = {},
            onReminderClicked = {}
        )
    }
}