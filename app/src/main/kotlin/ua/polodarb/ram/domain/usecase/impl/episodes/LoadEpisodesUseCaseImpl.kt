package ua.polodarb.ram.domain.usecase.impl.episodes

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.polodarb.ram.data.repository.EpisodesRepository
import ua.polodarb.ram.domain.paging.EpisodeRemoteMediator
import ua.polodarb.ram.domain.usecase.episodes.LoadEpisodesUseCase
import ua.polodarb.ram.domain.usecase.models.episodes.EpisodeDomainModel
import ua.polodarb.ram.domain.usecase.models.episodes.EpisodeDomainModel.Companion.toDomain
import javax.inject.Inject

class LoadEpisodesUseCaseImpl @Inject constructor(
    private val episodesRepository: EpisodesRepository,
    private val remoteMediator: EpisodeRemoteMediator
) : LoadEpisodesUseCase {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun invoke(input: Unit): Flow<PagingData<EpisodeDomainModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 40,
                prefetchDistance = 10,
                enablePlaceholders = false
            ),
            remoteMediator = remoteMediator
        ) {
            episodesRepository.loadAllEpisodes()
        }.flow.map { pagingData ->
            pagingData.map { entity ->
                entity.toDomain()
            }
        }
    }
}
