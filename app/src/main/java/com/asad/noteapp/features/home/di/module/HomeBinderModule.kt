package com.asad.noteapp.features.home.di.module

import com.asad.noteapp.features.home.data.dataSource.local.HomeLocalDataSource
import com.asad.noteapp.features.home.data.dataSource.local.HomeLocalDataSourceImpl
import com.asad.noteapp.features.home.data.repository.HomeRepositoryImpl
import com.asad.noteapp.features.home.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeBinderModule {

    @Binds
    abstract fun bindHomeRepository(homeRepository: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindHomeLocalDataSource(homeLocalDataSource: HomeLocalDataSourceImpl):HomeLocalDataSource

}