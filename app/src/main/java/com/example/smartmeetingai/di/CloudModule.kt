package com.example.smartmeetingai.di

import com.example.smartmeetingai.data.repository.CloudRepository
import com.example.smartmeetingai.data.repository.CloudRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CloudModule {
    @Binds
    abstract fun bindCloudRepository(
        impl: CloudRepositoryImpl
    ): CloudRepository
}