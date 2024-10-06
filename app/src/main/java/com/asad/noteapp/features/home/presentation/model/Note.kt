package com.asad.noteapp.features.home.presentation.model

import com.asad.noteapp.features.note.domain.model.NoteModel

data class Note(
    val id: Int? = null,
    val title: String? = null,
    val note: String? = null,
    val createDate: String? = null,
    val tags: String? = null,
    val reminder: String? = null,
    val color: String? = null,
    val isPinned: Boolean = false
)

fun Note.toNoteModel(): NoteModel = with(this) {
    NoteModel(
        id = null,
        title = title,
        note = note,
        createDate = createDate,
    )
}

