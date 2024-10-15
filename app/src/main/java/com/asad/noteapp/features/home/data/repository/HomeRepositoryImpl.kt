package com.asad.noteapp.features.home.data.repository

import com.asad.noteapp.features.home.data.dataSource.local.HomeLocalDataSource
import com.asad.noteapp.features.home.domain.repository.HomeRepository
import com.asad.noteapp.core.domain.note.model.NoteModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeLocalDataSource: HomeLocalDataSource
) : HomeRepository {

    override fun fetchNotes(query: String): Flow<List<NoteModel>> =
        homeLocalDataSource
            .fetchNotes(query = query)
            .asNoteModels()
}

