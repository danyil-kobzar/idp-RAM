package ua.polodarb.ram.data.network.impl.source

import ua.polodarb.ram.common.core.result.ResultOf
import ua.polodarb.ram.data.network.api.ApiService
import ua.polodarb.ram.data.network.model.characters.CharacterNetworkModel
import ua.polodarb.ram.data.repository.models.characters.CharacterRepoModel
import ua.polodarb.ram.data.network.model.core.InfoNetworkModel
import ua.polodarb.ram.data.network.source.NetworkDataSource
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : NetworkDataSource {
    override suspend fun getAllCharacters(page: Int): ResultOf<InfoNetworkModel<CharacterNetworkModel>> =
        apiCall {
            apiService.getAllCharacters(page)
        }
}