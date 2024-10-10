package com.asad.noteapp.features.note.data.dataSource.local

import com.asad.noteapp.core.data.dataSource.note.localDataSource.entity.NoteEntity


interface NoteLocalDataSource {

    suspend fun insertNote(note: NoteEntity)

    suspend fun updateNote(note: NoteEntity)

    suspend fun deleteNote(note: NoteEntity)

    suspend fun fetchNote(noteId: Int): NoteEntity?
}