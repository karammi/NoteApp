package com.asad.noteapp.features.note.presentatoin.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.noteapp.app.navigation.NavigationConstants
import com.asad.noteapp.core.domain.calendar.usecase.CalendarUseCase
import com.asad.noteapp.features.home.domain.mapper.NoteModelToNoteMapper
import com.asad.noteapp.features.home.presentation.model.Note
import com.asad.noteapp.features.note.domain.usecase.InsertNoteUseCase
import com.asad.noteapp.features.note.presentatoin.model.NoteUiState
import com.asad.noteapp.features.home.presentation.model.toNoteModel
import com.asad.noteapp.features.note.domain.usecase.FetchNoteUseCase
import com.asad.noteapp.features.note.domain.usecase.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val insertNoteUseCase: InsertNoteUseCase,
    private val fetchNoteUseCase: FetchNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val noteModelToNoteMapper: NoteModelToNoteMapper,
    private val calendarUseCase: CalendarUseCase,
) : ViewModel() {

    //region Note Screen State
    private val _uiState = MutableStateFlow(NoteUiState())
    val uiState: StateFlow<NoteUiState> = _uiState
    var id: Int? = null
    //endregion

    //region Note Init blocks
    init {
        showLoading(value = true)
        id = savedStateHandle.get<Int>(NavigationConstants.NOTE_ID)
        if (id == null || id == 0) {
            createNote()
        } else {
            fetchNoteById()
        }
        setDefaultDateAndTime()
    }

    fun showLoading(value: Boolean) {
        _uiState.update { currentState -> currentState.copy(isLoading = value) }
    }

    fun setDefaultDateAndTime() {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                val todayInMillis = calendarUseCase.getTodayDateInMillis()
                val date = calendarUseCase.getFormattedDateTime(todayInMillis)
                val time = calendarUseCase.getTimeInFormat(todayInMillis)
                val hourAndMinute = calendarUseCase.getHourAndMinute(todayInMillis)
                currentUiState.copy(
                    selectedDate = date,
                    selectedTime = time,
                    selectedDateInMillis = todayInMillis,
                    selectedTimeInMillis = todayInMillis,
                    selectedHour = hourAndMinute.first,
                    selectedMinutes = hourAndMinute.second
                )
            }
        }
    }

    private fun createNote() {
        viewModelScope.launch {
            val createDate = System.currentTimeMillis()
            _uiState.emit(
                value = NoteUiState(
                    note = Note(
                        id = 0,
                        title = "",
                        createDate = createDate
                    )
                )
            )
        }
    }

    private fun fetchNoteById() {
        viewModelScope.launch {
            if (id != null) {
                val noteToEdit = fetchNoteUseCase(noteId = id!!)

                noteToEdit?.let { noteModel ->
                    _uiState.emit(
                        value = NoteUiState(note = noteModelToNoteMapper.noteModelToNote(noteModel))
                    )
                }
                showLoading(false)
            }
        }
    }

    //endregion

    //region User Actions
    fun onTitleChanged(title: String) =
        _uiState.update { currentState ->
            val currentNote = currentState.note?.copy(title = title)
            currentState.copy(note = currentNote)
        }

    fun onNoteChanged(noteBody: String) =
        _uiState.update { currentState ->
            val currentNote = currentState.note?.copy(note = noteBody)
            currentState.copy(note = currentNote)
        }

    fun onDateSelected(dateInMillis: Long) {
        _uiState.update { currentState ->
            val formattedDate = calendarUseCase.getFormattedDateTime(dateInMillis)
            val currentNote = currentState.note?.copy(reminder = dateInMillis)
            currentState.copy(
                note = currentNote,
                selectedDate = formattedDate,
                selectedDateInMillis = dateInMillis
            )
        }
    }

    fun onTimeSelected(hour: Int, minute: Int, isAfternoon: Boolean) {
        _uiState.update { currentState ->
            val selectedTimeInMillis = calendarUseCase.setDateTime(
                hour = hour,
                minute = minute,
                dateInMillis = _uiState.value.selectedDateInMillis
            )
            val time = calendarUseCase.getTimeInFormat(selectedTimeInMillis)
            currentState.copy(
                isLoading = false,
                selectedTimeInMillis = selectedTimeInMillis,
                selectedTime = time,
                selectedHour = hour,
                selectedMinutes = minute,
                isAfternoon = isAfternoon
            )
        }
    }

    fun onReminderItemClicked(timeInMillis: Long) {
        _uiState.update { currentState ->
            val note = currentState.note?.copy(reminder = timeInMillis)
            currentState.copy(note = note)
        }
    }

    fun onSaveDateTimeDialogClicked() {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                val dateTimeInMills = calendarUseCase.setDateTime(
                    dateInMillis = _uiState.value.selectedDateInMillis,
                    hour = _uiState.value.selectedHour,
                    minute = _uiState.value.selectedMinutes
                )
                val note = currentUiState.note?.copy(
                    reminder = dateTimeInMills
                )
                currentUiState.copy(note = note)
            }
        }
    }

    fun onSaveClicked() {
        if (_uiState.value.note?.title!!.isBlank()) return

        if (_uiState.value.note?.note!!.isBlank()) return

        viewModelScope.launch {
            if (id == 0 || id == null) {
                _uiState.value.note?.let { insertNoteUseCase.invoke(note = it.toNoteModel()) }
            } else {
                updateNoteUseCase.invoke(note = _uiState.value.note!!.toNoteModel())
            }
        }
    }

    //endregion

    //region Showing Dialogs and Sheets
    fun onShowReminderBottomSheet(value: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(showReminderBottomSheet = value)
        }
    }

    fun showDateTimeDialog(value: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(showDateTimeDialog = value)
        }
    }

    fun onSetReminderClicked(value: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(showDataPicker = value)
        }
    }

    fun onShowTimePicker(value: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(showTimePicker = value)
        }
    }

    //endregion
}
