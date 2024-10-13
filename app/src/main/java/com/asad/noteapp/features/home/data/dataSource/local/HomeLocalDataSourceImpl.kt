package com.asad.noteapp.features.home.data.dataSource.local

import com.asad.noteapp.core.data.dataSource.note.localDataSource.entity.NoteEntity
import com.asad.noteapp.core.domain.note.model.NoteModel
import com.asad.noteapp.features.home.data.dataSource.local.dao.HomeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeLocalDataSourceImpl @Inject constructor(
    private val homeDao: HomeDao
) : HomeLocalDataSource {
    override fun fetchNotes(): Flow<List<NoteEntity>> {
        return homeDao.fetchNotes()
    }

    override fun searchNotes(query: String): Flow<List<NoteEntity>> {
        return homeDao.fetchNotes()
    }
}