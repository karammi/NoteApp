package com.asad.noteapp.features.home.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.asad.noteapp.core.data.dataSource.local.dao.NoteConstant
import com.asad.noteapp.core.data.dataSource.local.dao.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {
    @Query("SELECT * FROM ${NoteConstant.TABLE_NAME}")
    fun fetchNotes(): Flow<List<NoteEntity>>
}