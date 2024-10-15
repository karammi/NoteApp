package com.asad.noteapp.features.home.domain.repository

import com.asad.noteapp.core.domain.note.model.NoteModel
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
     fun fetchNotes(query: String): Flow<List<NoteModel>>
}