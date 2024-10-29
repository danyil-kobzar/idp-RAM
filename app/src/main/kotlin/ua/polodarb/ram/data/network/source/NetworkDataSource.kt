package ua.polodarb.ram.data.network.source

import ua.polodarb.ram.common.core.result.ResultOf
import ua.polodarb.ram.data.network.model.characters.CharacterNetworkModel
import ua.polodarb.ram.data.network.model.core.InfoNetworkModel
import ua.polodarb.ram.data.repository.models.characters.CharacterRepoModel
import ua.polodarb.ram.data.network.source.base.BaseNetworkSource

interface NetworkDataSource : BaseNetworkSource {
    suspend fun getAllCharacters(page: Int): ResultOf<InfoNetworkModel<CharacterNetworkModel>>
}