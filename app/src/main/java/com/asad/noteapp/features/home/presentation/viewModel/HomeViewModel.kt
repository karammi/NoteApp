package com.asad.noteapp.features.home.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.noteapp.core.data.dataSource.calendar.repository.CalendarRepositoryImpl
import com.asad.noteapp.core.domain.note.model.NoteModel
import com.asad.noteapp.features.home.domain.usecase.FetchNotesUseCase
import com.asad.noteapp.features.home.domain.usecase.SearchNoteUseCase
import com.asad.noteapp.features.home.presentation.model.HomeUiState
import com.asad.noteapp.features.home.presentation.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNotesUseCase: FetchNotesUseCase,
    private val searchNoteUseCase: SearchNoteUseCase,
    private val calendarRepository: CalendarRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchNotes()
    }

    fun fetchNotes() {
        viewModelScope.launch {
            fetchNotesUseCase()
                .map { notes ->
                    notes.map { note -> noteModelToNote(note) }
                }
                .collect { notes ->
                    updateUiState(notes = notes)
                }
        }
    }

    private fun updateUiState(notes: List<Note>) {
        _uiState.update {
            it.copy(
                isLoading = false,
                notes = notes.toPersistentList(),
                isError = false,
            )
        }
    }

    fun updateListViewLayout(useGridLayout: Boolean) {
        _uiState.update { currentState -> currentState.copy(isGridLayout = useGridLayout) }
    }

    fun noteModelToNote(noteModel: NoteModel): Note {
        return with(noteModel) {
            Note(
                id = id,
                title = title,
                note = note,
                createDate = createDate,
                tags = tags,
                reminder = reminder,
                color = color,
                hasReminder = reminder != null && reminder != 0L,
                hasTag = !tags.isNullOrEmpty(),
                reminderDateTime = reminder?.let { calendarRepository.getFormattedDateTime(it) }
            )
        }
    }
}
