package com.asad.noteapp.features.home.data.repository

import com.asad.noteapp.features.home.data.dataSource.local.HomeLocalDataSource
import com.asad.noteapp.features.home.domain.repository.HomeRepository
import com.asad.noteapp.features.note.domain.model.NoteModel
import com.asad.noteapp.features.note.domain.model.toNoteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeLocalDataSource: HomeLocalDataSource
) : HomeRepository {
    override suspend fun fetchNotes(): Flow<List<NoteModel>> {
        return homeLocalDataSource
            .fetchNotes()
            .map { notes -> notes.map { currentNote -> currentNote.toNoteModel() } }
    }
}