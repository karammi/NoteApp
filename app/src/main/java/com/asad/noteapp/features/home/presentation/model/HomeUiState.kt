package com.asad.noteapp.features.home.presentation.model

data class HomeUiState(
    val isLoading: Boolean = false,
    val notes: List<Note> = emptyList(),
    val isError: Boolean = false,
    val isGridLayout: Boolean = false,
)