package com.asad.noteapp.core.data.dataSource.note.localDataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.asad.noteapp.core.data.dataSource.note.localDataSource.dao.NoteDao
import com.asad.noteapp.core.data.dataSource.note.localDataSource.entity.NoteEntity
import com.asad.noteapp.core.data.dataSource.note.localDataSource.entity.TagEntity
import com.asad.noteapp.features.home.data.dataSource.local.dao.HomeDao

@Database(
    entities = [NoteEntity::class, TagEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    abstract fun homeDao(): HomeDao

}