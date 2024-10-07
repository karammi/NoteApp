package com.asad.noteapp.core.domain.note.model

import com.asad.noteapp.core.data.dataSource.note.localDataSource.entity.NoteEntity


data class NoteModel(
    val id: Int,
    val title: String,
    val note: String? = null,
    val createDate: Long? = null,
    val reminder: Long? = null,
    val tags: String? = null,
    val color: String? = null,
)

fun NoteModel.toNoteEntity(): NoteEntity = with(this) {
    NoteEntity(
        id = id,
        title = title,
        note = note,
        createDate = createDate,
        reminder = reminder,
        color = color,
        tags = tags,
    )
}

fun NoteEntity.toNoteModel(): NoteModel = with(this) {
    NoteModel(
        id = id,
        title = title,
        note = note,
        createDate = createDate,
        reminder = reminder,
        tags = tags,
        color = color
    )
}