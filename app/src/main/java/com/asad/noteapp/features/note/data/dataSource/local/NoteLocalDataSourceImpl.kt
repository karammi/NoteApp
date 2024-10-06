package com.asad.noteapp.features.note.data.dataSource.local

import com.asad.noteapp.core.data.dataSource.local.dao.NoteDao
import com.asad.noteapp.core.data.dataSource.local.dao.NoteEntity
import javax.inject.Inject

class NoteLocalDataSourceImpl @Inject constructor(
    private val noteDao: NoteDao
) : NoteLocalDataSource {

    override suspend fun insertNote(note: NoteEntity) {
        noteDao.insertNote(note = note)
    }

    override suspend fun updateNote(note: NoteEntity) {
        noteDao.updateNote(note = note)
    }

    override suspend fun deleteNote(note: NoteEntity) {
        noteDao.deleteNote(note = note)
    }
}