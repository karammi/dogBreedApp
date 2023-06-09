package com.asad.dogs.core.di.module

import com.asad.dogs.core.di.qualifier.DefaultDispatcherQualifier
import com.asad.dogs.core.di.qualifier.IODispatcherQualifier
import com.asad.dogs.core.di.qualifier.MainDispatcherQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object CoroutineDispatcherModule {

    @Provides
    @IODispatcherQualifier
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDispatcherQualifier
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @DefaultDispatcherQualifier
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
