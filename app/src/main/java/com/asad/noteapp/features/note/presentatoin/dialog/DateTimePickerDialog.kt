package com.asad.noteapp.features.note.presentatoin.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DateTimePickerDialog(
    onCancel: () -> Unit,
    onSave: () -> Unit,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancel,
        dismissButton = {
            TextButton(onClick = { onCancel() }) {
                Text("Cancel")
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onSave() }
            ) {
                Text(
                    "Save",
                    modifier = Modifier
                        .width(60.dp)
                        .height(30.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(Color(0xff3384F9)),
                    textAlign = TextAlign.Center
                )
            }
        },
        text = { content() }
    )
}