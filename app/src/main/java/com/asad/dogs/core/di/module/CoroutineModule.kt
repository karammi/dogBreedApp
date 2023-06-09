package com.asad.dogs.core.di.module

import com.asad.dogs.core.di.qualifier.ApplicationScope
import com.asad.dogs.core.di.qualifier.CoreCoroutineScope
import com.asad.dogs.core.di.qualifier.DefaultDispatcherQualifier
import com.asad.dogs.core.di.qualifier.IODispatcherQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    /**
     * provide a [CoroutineScope] with a [SupervisorJob] context
     * */
    @ApplicationScope
    @Provides
    @Singleton
    fun provideCoroutineScope(@DefaultDispatcherQualifier defaultDispatcher: CoroutineDispatcher): CoroutineScope =
        CoroutineScope(context = SupervisorJob() + defaultDispatcher)

    /**
     * provide a [CoroutineScope] with just a [Job] context
     * also used ioDispatcher
     * */
    @CoreCoroutineScope
    @Provides
    @Singleton
    fun provideCoreCoroutineScope(@IODispatcherQualifier ioDispatcher: CoroutineDispatcher): CoroutineScope =
        CoroutineScope(context = Job() + ioDispatcher)
}
