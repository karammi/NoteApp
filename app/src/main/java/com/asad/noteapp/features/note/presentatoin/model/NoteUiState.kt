package com.asad.noteapp.features.note.presentatoin.model

import com.asad.noteapp.features.home.presentation.model.Note

data class NoteUiState(
    val isLoading: Boolean = false,
    val note: Note? = null,
    val showDataPicker: Boolean = false,
    val showTimePicker: Boolean = false,
    val showDateTimeDialog: Boolean = false,
    val showReminderBottomSheet: Boolean = false,
    val selectedDate: String? = null,
    val selectedTime: String? = null
)