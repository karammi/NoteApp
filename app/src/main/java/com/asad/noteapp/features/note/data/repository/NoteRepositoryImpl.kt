package com.asad.noteapp.features.note.data.repository

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.asad.noteapp.features.note.data.dataSource.local.NoteLocalDataSource
import com.asad.noteapp.core.domain.note.model.NoteModel
import com.asad.noteapp.core.domain.note.model.toNoteEntity
import com.asad.noteapp.core.domain.note.model.toNoteModel
import com.asad.noteapp.core.service.notification.util.NotificationConstants
import com.asad.noteapp.core.service.services.ReminderService
import com.asad.noteapp.features.note.domain.repository.NoteRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Calendar
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteLocalDataSource: NoteLocalDataSource,
    @ApplicationContext private val context: Context,
) : NoteRepository {

    override suspend fun insertNote(note: NoteModel) {
        noteLocalDataSource.insertNote(note = note.toNoteEntity())
        if (note.reminder != null)
            setReminder(noteId = 0, noteTitle = note.title, noteBody = note.note)
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

    fun setReminder(noteId: Int, noteTitle: String, noteBody: String?) {
        val data = workDataOf(
            NotificationConstants.ID_KEY to noteId,
            NotificationConstants.TITLE_KEY to noteTitle,
            NotificationConstants.NOTE_KEY to noteBody
        )

        val workRequest = OneTimeWorkRequestBuilder<ReminderService>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .setInputData(data)
            .build()

        WorkManager.getInstance(context).enqueue(workRequest)
    }
}