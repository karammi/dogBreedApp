package com.asad.dogs.core.di.module

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.asad.dogs.core.data.dataSource.local.DogBreedDatabase
import dagger.Module
import dagger.Provides
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
    fun provideDatabase() = Room.inMemoryDatabaseBuilder(
        context = ApplicationProvider.getApplicationContext(),
        DogBreedDatabase::class.java,
    ).build()
}
