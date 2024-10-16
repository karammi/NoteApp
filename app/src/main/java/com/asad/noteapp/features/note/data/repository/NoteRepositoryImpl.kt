package com.asad.noteapp.features.note.data.repository

import com.asad.noteapp.features.note.data.dataSource.local.NoteLocalDataSource
import com.asad.noteapp.core.domain.note.model.NoteModel
import com.asad.noteapp.core.domain.note.model.toNoteEntity
import com.asad.noteapp.core.domain.note.model.toNoteModel
import com.asad.noteapp.core.service.model.AlarmModel
import com.asad.noteapp.core.service.services.AlarmSchedulerImpl
import com.asad.noteapp.features.note.domain.repository.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteLocalDataSource: NoteLocalDataSource,
    private val alarmSchedulerImpl: AlarmSchedulerImpl
) : NoteRepository {

    /**
     * instead of sending request to reminder launcher,
     * we can observe the note list from room database and set the reminder
     * */
    override suspend fun insertNote(note: NoteModel) {
        noteLocalDataSource.insertNote(note = note.toNoteEntity())
        if (note.reminder != null)
            alarmSchedulerImpl.schedule(
                alarmModel = AlarmModel(
                    id = note.id,
                    title = note.title,
                    note = note.note,
                    alarmTime = note.reminder
                )
            )
    }

    override suspend fun updateNote(note: NoteModel) {
        noteLocalDataSource.updateNote(note.toNoteEntity())
    }

    override suspend fun deleteNote(note: NoteModel) {
        noteLocalDataSource.deleteNote(note.toNoteEntity())
    }

    override suspend fun fetchNote(noteId: Int): NoteModel? =
        noteLocalDataSource
            .fetchNote(noteId = noteId)
            ?.toNoteModel()
}
