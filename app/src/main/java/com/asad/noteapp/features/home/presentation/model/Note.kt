package com.asad.noteapp.features.home.presentation.model

import com.asad.noteapp.core.domain.note.model.NoteModel

data class Note(
    val id: Int,
    val title: String,
    val note: String? = null,
    val createDate: Long? = null,
    val tags: String? = null,
    val reminder: Long? = null,
    val color: String? = null,
    val hasReminder: Boolean = false,
    val hasTag: Boolean = false,
    val reminderDateTime: String? = null
)

fun Note.toNoteModel(): NoteModel = with(this) {
    NoteModel(
        id = id,
        title = title,
        note = note,
        createDate = createDate,
        reminder = reminder,
        color = color,
        tags = tags
    )
}

