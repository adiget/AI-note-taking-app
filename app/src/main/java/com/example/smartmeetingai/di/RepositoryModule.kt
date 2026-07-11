package com.example.smartmeetingai.di

import com.example.smartmeetingai.data.repository.NoteRepository
import com.example.smartmeetingai.data.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindNoteRepository(repositoryImpl: NoteRepositoryImpl): NoteRepository
}
