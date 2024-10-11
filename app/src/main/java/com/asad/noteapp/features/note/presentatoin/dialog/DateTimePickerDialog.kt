package com.asad.noteapp.features.note.presentatoin.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.asad.noteapp.R

@Composable
fun DateTimePickerDialog(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit,
    onSave: () -> Unit,
    content: @Composable () -> Unit
) {
    AlertDialog(
        modifier = modifier
            .testTag(stringResource(R.string.calendar_dialog_test_tag)),
        onDismissRequest = onCancel,
        dismissButton = {
            TextButton(onClick = { onCancel() }) {
                Text(
                    text = stringResource(R.string.cancel_label),
                    modifier = Modifier.testTag(stringResource(R.string.cancel_button_text_test_tag))
                )
            }
        },
        confirmButton = {
            TextButton(
                modifier = Modifier.testTag(stringResource(R.string.save_button_test_tag)),
                onClick = { onSave() }
            ) {
                Text(
                    text = stringResource(R.string.save_label),
                    modifier = Modifier
                        .width(60.dp)
                        .height(30.dp)
                        .testTag(stringResource(R.string.save_button_text_test_teg))
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(Color(0xff3384F9)),
                    textAlign = TextAlign.Center
                )
            }
        },
        text = { content() }
    )
}

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun DateTimePickerDialogPreview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        DateTimePickerDialog(
            onCancel = {},
            onSave = {},
            content = {}
        )
    }
}