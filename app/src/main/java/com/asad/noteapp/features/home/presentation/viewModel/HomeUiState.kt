package com.asad.noteapp.features.home.presentation.viewModel

import com.asad.noteapp.features.note.domain.model.NoteModel

data class HomeUiState(
    val isLoading: Boolean = false,
    val notes: List<NoteModel> = emptyList(),
    val isError: Boolean = false,
    val isGridLayout: Boolean = false,
//    val notesLayoutMode: NotesLayoutMode = NotesLayoutMode.LIST
)

enum class NotesLayoutMode {
    LIST,
    GRID
}
