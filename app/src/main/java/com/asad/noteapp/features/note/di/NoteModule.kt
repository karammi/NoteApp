package com.asad.noteapp.features.note.di

import com.asad.noteapp.core.data.dataSource.local.NoteDatabase
import com.asad.noteapp.core.data.dataSource.local.dao.NoteDao
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