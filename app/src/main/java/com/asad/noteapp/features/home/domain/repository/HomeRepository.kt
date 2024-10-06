package com.asad.noteapp.features.home.domain.repository

import com.asad.noteapp.features.note.domain.model.NoteModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun fetchNotes(): Flow<List<NoteModel>>
}