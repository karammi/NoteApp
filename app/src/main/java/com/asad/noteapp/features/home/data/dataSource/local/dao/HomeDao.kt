package com.asad.noteapp.features.home.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.asad.noteapp.core.data.dataSource.note.localDataSource.entity.NoteEntity
import com.asad.noteapp.core.data.dataSource.note.localDataSource.util.NoteConstant
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDao {

    @Query("SELECT * FROM ${NoteConstant.TABLE_NAME} WHERE title LIKE '%' || :query || '%'")
    fun fetchNotes(query: String): Flow<List<NoteEntity>>
}