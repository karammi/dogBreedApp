package com.asad.dogs.breedList.data.dataSource.remote

import com.asad.dogs.breedList.data.dataSource.remote.api.BreedListApi
import com.asad.dogs.core.data.dataSource.ApiRunner
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class BreedListRemoteDataSourceImplTest {

    private val breedsApi = mockk<BreedListApi>()
    private val apiRunner = mockk<ApiRunner>()
    private lateinit var breedListRemoteDataSourceImpl: BreedListRemoteDataSource

    @Before
    fun setup() {
        breedListRemoteDataSourceImpl = BreedListRemoteDataSourceImpl(breedsApi, apiRunner)
    }

    @Test
    fun fetchBreedListCall_ShouldReturnSuccessDataResult() = runTest {



    }
}
