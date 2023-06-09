package com.asad.dogs.breedList.domain.usecase

import com.asad.dogs.breedList.domain.model.BreedModel
import com.asad.dogs.breedList.domain.repository.BreedListRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class FetchBreedListUseCase @Inject constructor(
    private val repository: BreedListRepository,
) {

    suspend operator fun invoke(): Flow<List<BreedModel>> = repository.fetchBreeds()
}
