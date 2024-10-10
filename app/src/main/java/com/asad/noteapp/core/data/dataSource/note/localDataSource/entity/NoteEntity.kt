package com.asad.noteapp.core.data.dataSource.note.localDataSource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.asad.noteapp.core.data.dataSource.note.localDataSource.util.NoteConstant

@Entity(tableName = NoteConstant.TABLE_NAME)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val note: String? = null,
    val createDate: Long? = null,
    val reminder: Long? = null,
    val tags: String? = null,
    val color: String? = null,
)