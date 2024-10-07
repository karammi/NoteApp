package com.asad.noteapp.features.note.di.module

import com.asad.noteapp.core.data.dataSource.note.localDataSource.NoteDatabase
import com.asad.noteapp.core.data.dataSource.note.localDataSource.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object NoteModule {

    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase): NoteDao = noteDatabase.noteDao()
}