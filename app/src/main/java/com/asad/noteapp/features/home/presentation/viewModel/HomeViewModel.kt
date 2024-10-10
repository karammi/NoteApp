package com.asad.noteapp.features.home.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.noteapp.features.home.domain.usecase.FetchNotesUseCase
import com.asad.noteapp.features.home.domain.mapper.NoteModelToNoteMapper
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
    private val noteModelToNoteMapper: NoteModelToNoteMapper,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchNotes()
    }

    fun fetchNotes() {
        viewModelScope.launch {
            fetchNotesUseCase()
                .map { it.map { noteModelToNoteMapper.noteModelToNote(it) } }
                .collect { notes -> updateUiState(notes = notes) }
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
}