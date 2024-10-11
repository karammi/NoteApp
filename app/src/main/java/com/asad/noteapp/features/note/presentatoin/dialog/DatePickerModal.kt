package com.asad.noteapp.features.note.presentatoin.dialog

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    modifier: Modifier = Modifier,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()
    val context = LocalContext.current
    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    val selectedDate = Calendar.getInstance().apply {
                        timeInMillis = datePickerState.selectedDateMillis!!
                    }
                    if (!selectedDate.after(Calendar.getInstance())) {
                        Toast.makeText(
                            context,
                            "Selected date should be after today, please select again",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        onDateSelected(datePickerState.selectedDateMillis)
                        onDismiss()
                    }
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DatePickerModalPreview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        DatePickerModal(
            onDateSelected = {},
            onDismiss = {}
        )
    }
}