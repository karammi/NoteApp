package com.asad.noteapp.features.home.data.dataSource.local

import com.asad.noteapp.core.data.dataSource.local.dao.NoteEntity
import kotlinx.coroutines.flow.Flow

interface HomeLocalDataSource {

    fun fetchNotes(): Flow<List<NoteEntity>>
}