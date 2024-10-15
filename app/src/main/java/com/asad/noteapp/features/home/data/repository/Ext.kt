package com.asad.noteapp.features.home.data.repository

import com.asad.noteapp.core.data.dataSource.note.localDataSource.entity.NoteEntity
import com.asad.noteapp.core.domain.note.model.NoteModel
import com.asad.noteapp.core.domain.note.model.toNoteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<List<NoteEntity>>.asNoteModels(): Flow<List<NoteModel>> =
    map { notes -> notes.map { note -> note.toNoteModel() } }
