package ua.polodarb.ram.data.network.api

import ua.polodarb.ram.common.core.result.ResultOf
import ua.polodarb.ram.data.network.model.characters.CharacterNetworkModel
import ua.polodarb.ram.data.repository.models.characters.CharacterRepoModel
import ua.polodarb.ram.data.network.model.core.InfoNetworkModel

interface ApiService {

    suspend fun getAllCharacters(page: Int): ResultOf<InfoNetworkModel<CharacterNetworkModel>>

}