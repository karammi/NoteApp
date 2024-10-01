package com.asad.noteapp.core.data.dataSource.local.dao

import androidx.room.Entity

@Entity(tableName = "")
data class NoteEntity(
    val id: Int,
    val title: String? = null,
    val description: String? = null,
    val createDate: String? = null,
    val tags: String? = null,
    val reminder: String? = null,
    val color: String? = null,
    val isPinned: Boolean = false
)
