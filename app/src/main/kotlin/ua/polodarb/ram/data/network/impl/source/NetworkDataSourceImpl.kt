package ua.polodarb.ram.data.network.impl.source

import ua.polodarb.ram.common.core.result.ResultOf
import ua.polodarb.ram.data.network.api.ApiService
import ua.polodarb.ram.data.network.model.characters.CharacterNetworkModel
import ua.polodarb.ram.data.network.model.core.InfoNetworkModel
import ua.polodarb.ram.data.network.model.episodes.EpisodeNetworkModel
import ua.polodarb.ram.data.network.source.NetworkDataSource
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : NetworkDataSource {
    override suspend fun getAllCharacters(
        page: Int,
        searchByName: String?
    ): ResultOf<InfoNetworkModel<CharacterNetworkModel>> =
        apiCall {
            apiService.getAllCharacters(page, searchByName)
        }

    override suspend fun getAllEpisodes(page: Int): ResultOf<InfoNetworkModel<EpisodeNetworkModel>> =
        apiCall {
            apiService.getAllEpisodes(page)
        }
}