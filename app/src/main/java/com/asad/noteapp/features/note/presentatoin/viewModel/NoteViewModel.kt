package com.asad.noteapp.features.note.presentatoin.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asad.noteapp.features.note.domain.usecase.InsertNoteUseCase
import com.asad.noteapp.features.note.presentatoin.model.NoteUiState
import com.asad.noteapp.features.home.presentation.model.Note
import com.asad.noteapp.features.home.presentation.model.toNoteModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val insertNoteUseCase: InsertNoteUseCase
) : ViewModel() {

    private val id = savedStateHandle.get<Int>("id")

    private fun fetchNote() {
        if (id == null) return
    }

    private val _uiState = MutableStateFlow(NoteUiState())
    val uiState: StateFlow<NoteUiState> = _uiState

    fun onTitleChanged(title: String) =
        _uiState.update { currentState -> currentState.copy(title = title) }


    fun onNoteChanged(body: String) =
        _uiState.update { currentState -> currentState.copy(note = body) }

    fun onLabelClicked() {}

    fun onLabelChanged(label: String) {}

    fun onSaveClicked() {
        if (_uiState.value.title == null) return
        if (_uiState.value.note == null) return
        viewModelScope.launch {
            val currentNote = Note(
                title = _uiState.value.title,
                note = _uiState.value.note,
            )
            insertNoteUseCase.invoke(note = currentNote.toNoteModel())
        }
    }
}
