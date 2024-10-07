package com.asad.noteapp.features.note.presentatoin.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.asad.noteapp.features.note.presentatoin.component.NoteBodyComponent
import com.asad.noteapp.features.note.presentatoin.component.NoteBottomComponent
import com.asad.noteapp.features.note.presentatoin.component.NoteToolbarComponent
import com.asad.noteapp.features.note.presentatoin.component.ReminderBottomSheetContent
import com.asad.noteapp.features.note.presentatoin.dialog.DatePickerModal
import com.asad.noteapp.features.note.presentatoin.dialog.DateTimePickerDialog
import com.asad.noteapp.features.note.presentatoin.dialog.DateTimePickerDialogContent
import com.asad.noteapp.features.note.presentatoin.dialog.TimerPickerDialog
import com.asad.noteapp.features.note.presentatoin.model.NoteUiState
import com.asad.noteapp.features.note.presentatoin.viewModel.NoteViewModel

@Composable
fun NoteScreen(
    id: Int? = null,
    onBackClicked: () -> Unit,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    NoteContent(
        uiState = uiState.value,
        onTitleChanged = viewModel::onTitleChanged,
        onNoteChanged = viewModel::onNoteChanged,
        onSaveClicked = viewModel::onSaveClicked,
        onDateChanged = viewModel::onDateSelected,
        onTimeChanged = viewModel::onTimeSelected,
        onShowDatePickerClicked = viewModel::onSetReminderClicked,
        onShowDateTimeDialog = viewModel::showDateTimeDialog,
        onShowTimePickerClicked = viewModel::onShowTimePicker,
        onShowReminderBottomSheet = viewModel::onShowReminderBottomSheet,
        onBackClicked = onBackClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NoteContent(
    uiState: NoteUiState,
    onTitleChanged: (String) -> Unit,
    onNoteChanged: (String) -> Unit,
    onDateChanged: (Long) -> Unit,
    onTimeChanged: (Long) -> Unit,
    onShowDateTimeDialog: (Boolean) -> Unit,
    onShowDatePickerClicked: (Boolean) -> Unit,
    onShowTimePickerClicked: (Boolean) -> Unit,
    onShowReminderBottomSheet: (Boolean) -> Unit,
    onSaveClicked: () -> Unit,
    onBackClicked: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()

    if (uiState.showDateTimeDialog == true)
        DateTimePickerDialog(
            onDismiss = {
                onShowDateTimeDialog(false)
                onShowDatePickerClicked(false)
                onShowTimePickerClicked(false)
            },
            onConfirm = {
                onShowDateTimeDialog(false)
            },
            content = {
                DateTimePickerDialogContent(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(horizontal = 8.dp),
                    dateValue = uiState.selectedDate ?: "",
                    timeValue = uiState.selectedTime ?: "",
                    onShowDatePickerClicked = onShowDatePickerClicked,
                    onShowTimePickerClicked = onShowTimePickerClicked
                )
            }
        )

    if (uiState.showDataPicker == true) {
        DatePickerModal(
            onDateSelected = { onDateChanged(it!!) },
            onDismiss = { onShowDatePickerClicked(false) }
        )
    }

    if (uiState.showTimePicker == true) {
        TimerPickerDialog(
            onConfirm = { onTimeChanged(it.hour.toLong() * 3600000 + it.minute.toLong() * 60000) },
            onDismiss = { onShowTimePickerClicked(false) }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                actions = {
                    NoteToolbarComponent(
                        onBackClicked = onBackClicked,
                        onSetReminderClicked = onShowReminderBottomSheet
                    )
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (uiState.showReminderBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { onShowReminderBottomSheet(false) },
                    sheetState = sheetState
                ) {
                    ReminderBottomSheetContent(
                        onReminderItemClicked = {},
                        onPickDateClicked = {
                            onShowReminderBottomSheet(false)
                            onShowDateTimeDialog(true)
                        }
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(top = 120.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
            NoteBodyComponent(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                uiState = uiState,
                onTitleChanged = onTitleChanged,
                onNoteBodyChanged = onNoteChanged
            )

            NoteBottomComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .shadow(1.dp, shape = RoundedCornerShape(1.dp))
                    .align(alignment = Alignment.BottomCenter),
                onSaveClicked = onSaveClicked
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun NoteContentPreview() {
    NoteContent(
        uiState = NoteUiState(),
        onNoteChanged = {},
        onTitleChanged = {},
        onDateChanged = {},
        onTimeChanged = {},
        onShowDateTimeDialog = {},
        onShowTimePickerClicked = {},
        onShowDatePickerClicked = {},
        onShowReminderBottomSheet = {},
        onBackClicked = {},
        onSaveClicked = {}
    )
}