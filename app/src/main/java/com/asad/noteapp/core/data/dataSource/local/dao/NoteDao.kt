package com.asad.noteapp.core.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM ${NoteConstant.TABLE_NAME}")
    fun getNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM ${NoteConstant.TABLE_NAME} WHERE ${NoteConstant.COLUMN_ID} = :id")
    suspend fun getNoteById(id: Int): NoteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Query("DELETE FROM ${NoteConstant.TABLE_NAME}")
    suspend fun deleteAllNotes()

    @Update
    suspend fun updateNote(note: NoteEntity)

    @Upsert(entity = NoteEntity::class)
    suspend fun upsertNote(note: NoteEntity)
}