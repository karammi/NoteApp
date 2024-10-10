package com.asad.noteapp.core.data.dataSource.note.localDataSource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.asad.noteapp.core.data.dataSource.note.localDataSource.util.TagConstant

@Entity(tableName = TagConstant.TABLE_NAME)
data class TagEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)