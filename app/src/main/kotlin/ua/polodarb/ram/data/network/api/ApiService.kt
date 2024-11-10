package ua.polodarb.ram.data.network.api

import ua.polodarb.ram.common.core.result.ResultOf
import ua.polodarb.ram.data.network.model.characters.CharacterNetworkModel
import ua.polodarb.ram.data.network.model.core.InfoNetworkModel
import ua.polodarb.ram.data.network.model.episodes.EpisodeNetworkModel

interface ApiService {

    suspend fun getAllCharacters(
        page: Int,
        name: String? = null
    ): ResultOf<InfoNetworkModel<CharacterNetworkModel>>

    suspend fun getAllEpisodes(
        page: Int
    ): ResultOf<InfoNetworkModel<EpisodeNetworkModel>>

}