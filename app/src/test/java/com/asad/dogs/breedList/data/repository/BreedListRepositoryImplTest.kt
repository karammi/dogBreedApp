package com.asad.dogs.breedList.data.repository

import com.asad.dogs.breedList.data.dataSource.local.BreedListLocalDataSource
import com.asad.dogs.breedList.data.dataSource.local.entity.BreedEntity
import com.asad.dogs.breedList.data.dataSource.remote.BreedListRemoteDataSource
import com.asad.dogs.breedList.data.dataSource.remote.model.BreedResponseModel
import com.asad.dogs.breedList.data.mapper.BreedListMapper
import com.asad.dogs.breedList.data.mapper.DatabaseEntityToBreedMapper
import com.asad.dogs.breedList.data.mapper.ServerResponseToBreedMapper
import com.asad.dogs.breedList.domain.repository.BreedListRepository
import com.asad.dogs.core.data.dataSource.DataResult
import com.asad.dogs.core.data.util.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Before

class BreedListRepositoryImplTest {
    private lateinit var remote: BreedListRemoteDataSource
    private lateinit var database: BreedListLocalDataSource
    private lateinit var breedListMapper: BreedListMapper

    private lateinit var serverResponseMapper: ServerResponseToBreedMapper
    private lateinit var databaseEntityMapper: DatabaseEntityToBreedMapper

    private lateinit var sut: BreedListRepository

    @Before
    fun setup() {
        remote = FakeBreedListRemoteDataSourceImpl()
        database = FakeBreedListLocalDataSourceImpl()
        breedListMapper = BreedListMapper()
        databaseEntityMapper = DatabaseEntityToBreedMapper()
        serverResponseMapper = ServerResponseToBreedMapper(breedListMapper)

        sut = BreedListRepositoryImpl(
            remote = remote,
            serverResponseMapper,
        )
    }

    /*    @Test
        fun whenFetchBreeds_shouldReturnListOfBreeds() = runTest {
            */
    /**Arrange*//*
        */
    /**Act*//*
        */
    /**Assert*//*
    }*/
}

class FakeBreedListRemoteDataSourceImpl : BreedListRemoteDataSource {
    override suspend fun fetchBreedList(): Flow<DataResult<BreedResponseModel>> {
        return flow {
            emit(
                DataResult.Success(
                    BreedResponseModel(
                        message = mapOf(),
                        status = ResponseStatus.success,
                    ),
                ),
            )
        }
    }
}

class FakeBreedListLocalDataSourceImpl : BreedListLocalDataSource {
    override suspend fun getBreeds(): Flow<List<BreedEntity>> {
        return flow { emit(emptyList()) }
    }

    override suspend fun insertBreedList(breeds: List<BreedEntity>) {
    }
}
