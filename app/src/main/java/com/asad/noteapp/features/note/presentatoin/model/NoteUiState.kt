package com.asad.noteapp.features.note.presentatoin.model

data class NoteUiState(
    val isLoading: Boolean = false,
    val title: String? = null,
    val note: String? = null,
    val tags: List<String> = emptyList(),
    val isNoteSaved: Boolean = false,
    val isNoteDeleted: Boolean = false,
)