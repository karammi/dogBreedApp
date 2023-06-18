package com.asad.dogs.core.di.module

import android.content.Context
import androidx.room.Room
import com.asad.dogs.core.data.dataSource.local.DogBreedDatabase
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
    fun provideDatabase(@ApplicationContext applicaiton: Context) = Room.inMemoryDatabaseBuilder(
        context = applicaiton,
        DogBreedDatabase::class.java,
    ).build()
}
