package com.asad.noteapp.features.home.domain.mapper

import com.asad.noteapp.core.data.dataSource.calendar.repository.CalendarRepositoryImpl
import com.asad.noteapp.features.home.presentation.model.Note
import com.asad.noteapp.core.domain.note.model.NoteModel
import javax.inject.Inject

class NoteModelToNoteMapper @Inject constructor(
    private val calendarRepositoryImpl: CalendarRepositoryImpl
) {
    fun noteModelToNote(noteModel: NoteModel): Note {
        return with(noteModel) {
            Note(
                id = id,
                title = title,
                note = note,
                createDate = createDate,
                tags = tags,
                reminder = reminder,
                color = color,
                hasReminder = reminder != null && reminder != 0L,
                hasTag = !tags.isNullOrEmpty(),
                reminderDateTime = reminder?.let { calendarRepositoryImpl.getFormattedDateTime(it) }
            )
        }
    }
}


