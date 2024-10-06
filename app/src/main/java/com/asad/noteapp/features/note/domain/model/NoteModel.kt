package com.asad.noteapp.features.note.domain.model

import com.asad.noteapp.core.data.dataSource.local.dao.NoteEntity

data class NoteModel(
    val id: Int?,
    val title: String? = null,
    val note: String? = null,
    val createDate: String? = null,
    val tags: String? = null,
    val reminder: String? = null,
    val color: String? = null,
    val isPinned: Boolean = false
)

fun NoteModel.toNoteEntity(): NoteEntity = with(this) {
    NoteEntity(
        id = id,
        title = title,
        note = note,
        createDate = createDate,
    )
}

fun NoteEntity.toNoteModel(): NoteModel = with(this) {
    NoteModel(
        id = id,
        title = title,
        note = note,
        createDate = createDate,
    )
}