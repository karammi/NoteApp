package com.asad.noteapp.features.note.domain.repository

import com.asad.noteapp.core.domain.note.model.NoteModel

interface NoteRepository {

    suspend fun insertNote(note: NoteModel)

    suspend fun updateNote(note: NoteModel)

    suspend fun deleteNote(note: NoteModel)

    suspend fun fetchNote(noteId: Int): NoteModel?

}