package com.asad.noteapp.features.home.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.noteapp.core.data.dataSource.calendar.repository.CalendarRepositoryImpl
import com.asad.noteapp.core.domain.note.model.NoteModel
import com.asad.noteapp.features.home.domain.usecase.FetchNotesUseCase
import com.asad.noteapp.features.home.presentation.model.HomeUiState
import com.asad.noteapp.features.home.presentation.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.map

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchNotesUseCase: FetchNotesUseCase,
    private val calendarRepository: CalendarRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val searchQueryFlow = MutableSharedFlow<String>(replay = 1)

    init {
        fetchNotes("")
    }

    private fun noteModelToNote(notes: List<NoteModel>): List<Note> =
        notes.map { note -> noteModelToNote(note) }


    private fun fetchNotes(query: String) {
        viewModelScope.launch {
            fetchNotesUseCase
                .invoke(query)
                .map(::noteModelToNote)
                .collectLatest { updateUiState(it) }
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

    @OptIn(FlowPreview::class)
    fun onSearchChanged(query: String) {
        viewModelScope.launch {
            searchQueryFlow.emit(query)
        }
        _uiState.update { currentState -> currentState.copy(searchQuery = query) }

        viewModelScope.launch {
            searchQueryFlow
                .debounce(500)
                .collect(::fetchNotes)
        }
    }

    fun onSearchBarCloseClicked(){
        onSearchChanged("")
//        _uiState.update { currentState -> currentState.copy(searchQuery = "") }
//        fetchNotes("")
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
