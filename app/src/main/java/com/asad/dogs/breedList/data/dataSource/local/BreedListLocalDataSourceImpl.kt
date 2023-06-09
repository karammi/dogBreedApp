package com.asad.dogs.breedList.data.dataSource.local

import com.asad.dogs.breedList.data.dataSource.local.dao.BreedListDao
import com.asad.dogs.breedList.data.dataSource.local.entity.BreedEntity
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class BreedListLocalDataSourceImpl @Inject constructor(
    private val breedListDao: BreedListDao,
) : BreedListLocalDataSource {
    override suspend fun getBreeds(): Flow<List<BreedEntity>> = breedListDao.getBreeds()
    override suspend fun insertBreedList(breeds: List<BreedEntity>) =
        breedListDao.insertBreedList(breeds)
}
