package com.asad.noteapp.core.di

import android.content.Context
import androidx.room.Room
import com.asad.noteapp.core.data.dataSource.local.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context): NoteDatabase {
        return Room.databaseBuilder(
            context = applicationContext,
            klass = NoteDatabase::class.java,
            name = "note_db"
        ).build()
    }
}