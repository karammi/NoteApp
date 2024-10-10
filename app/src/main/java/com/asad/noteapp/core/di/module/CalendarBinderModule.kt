package com.asad.noteapp.core.di.module

import com.asad.noteapp.core.data.dataSource.calendar.localDataSource.CalendarLocalDataSource
import com.asad.noteapp.core.data.dataSource.calendar.localDataSource.CalendarLocalDataSourceImpl
import com.asad.noteapp.core.data.dataSource.calendar.repository.CalendarRepositoryImpl
import com.asad.noteapp.core.domain.calendar.repository.CalendarRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CalendarBinderModule {

    @Binds
    abstract fun bindDateRepository(dateRepository: CalendarRepositoryImpl): CalendarRepository

    @Binds
    abstract fun bindDateLocalDataSource(dateLocalDataSource: CalendarLocalDataSourceImpl): CalendarLocalDataSource
}