package com.asad.noteapp.core.di.module

import android.content.Context
import androidx.room.Room
import com.asad.noteapp.core.data.dataSource.note.localDataSource.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class],
)
object FakeDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext application: Context) =
        Room.inMemoryDatabaseBuilder(context = application, NoteDatabase::class.java).build()

}
