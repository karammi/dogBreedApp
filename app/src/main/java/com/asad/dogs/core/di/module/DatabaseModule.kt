package com.asad.dogs.core.di.module

import android.content.Context
import androidx.room.Room
import com.asad.dogs.core.data.dataSource.local.DogBreedDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext applicaiton: Context,
    ) = Room.databaseBuilder(
        context = applicaiton,
        klass = DogBreedDatabase::class.java,
        name = "dog_db",
    ).build()
}
