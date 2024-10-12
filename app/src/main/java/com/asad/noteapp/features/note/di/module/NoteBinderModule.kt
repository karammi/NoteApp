package com.asad.noteapp.features.note.di.module

import com.asad.noteapp.features.note.data.dataSource.local.NoteLocalDataSource
import com.asad.noteapp.features.note.data.dataSource.local.NoteLocalDataSourceImpl
import com.asad.noteapp.features.note.data.repository.NoteRepositoryImpl
import com.asad.noteapp.features.note.domain.repository.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NoteBinderModule {

    @Binds
    abstract fun bindNoteRepository(noteRepository: NoteRepositoryImpl): NoteRepository

    @Binds
    abstract fun bindNoteLocalDataSource(noteLocalDataSource: NoteLocalDataSourceImpl): NoteLocalDataSource

}