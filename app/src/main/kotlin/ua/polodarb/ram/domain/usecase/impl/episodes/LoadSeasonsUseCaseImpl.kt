package ua.polodarb.ram.domain.usecase.impl.episodes

import ua.polodarb.ram.data.repository.EpisodesRepository
import ua.polodarb.ram.domain.usecase.episodes.LoadSeasonsUseCase
import ua.polodarb.ram.domain.usecase.models.episodes.SeasonDomainModel
import ua.polodarb.ram.domain.usecase.models.episodes.SeasonDomainModel.Companion.toDomain
import javax.inject.Inject

class LoadSeasonsUseCaseImpl @Inject constructor(
    private val episodesRepository: EpisodesRepository
) : LoadSeasonsUseCase {
    override suspend fun invoke(input: Unit): List<SeasonDomainModel> {
        return episodesRepository.loadAllSeasons().map { it.toDomain() }
    }
}
