package com.asad.noteapp.features.note.data.repository

import com.asad.noteapp.features.note.data.dataSource.local.NoteLocalDataSource
import com.asad.noteapp.features.note.domain.model.NoteModel
import com.asad.noteapp.features.note.domain.model.toNoteEntity
import com.asad.noteapp.features.note.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteLocalDataSource: NoteLocalDataSource
) : NoteRepository {

    override suspend fun insertNote(note: NoteModel) {
        noteLocalDataSource.insertNote(note = note.toNoteEntity())
    }

    override suspend fun updateNote(note: NoteModel) {
        noteLocalDataSource.updateNote(note.toNoteEntity())
    }

    override suspend fun deleteNote(note: NoteModel) {
        noteLocalDataSource.deleteNote(note.toNoteEntity())
    }
}