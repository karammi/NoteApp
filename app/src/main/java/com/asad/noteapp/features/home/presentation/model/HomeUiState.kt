package com.asad.noteapp.features.home.presentation.model

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class HomeUiState(
    val isLoading: Boolean = false,
    val notes: PersistentList<Note> = persistentListOf(),
    val isError: Boolean = false,
    val isGridLayout: Boolean = false,
)