package com.asad.noteapp.core.data.dataSource.local.dao

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = NoteConstant.TABLE_NAME)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String? = null,
    val note: String? = null,
    val createDate: String? = null,
    val tags: String? = null,
    val reminder: String? = null,
    val color: String? = null,
    val isPinned: Boolean = false
)


@Entity(tableName = TagConstant.TABLE_NAME)
data class TagEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)