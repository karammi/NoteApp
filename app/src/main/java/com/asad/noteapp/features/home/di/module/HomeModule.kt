package com.asad.noteapp.features.home.di.module

import com.asad.noteapp.core.data.dataSource.note.localDataSource.NoteDatabase
import com.asad.noteapp.features.home.data.dataSource.local.dao.HomeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object HomeModule {

    @Provides
    fun provideHomeDao(noteDatabase: NoteDatabase): HomeDao = noteDatabase.homeDao()
}