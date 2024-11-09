package ua.polodarb.ram.data.network.impl.api

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import ua.polodarb.ram.common.core.result.ResultOf
import ua.polodarb.ram.data.network.KtorClient
import ua.polodarb.ram.data.network.api.ApiService
import ua.polodarb.ram.data.network.core.ApiRoutes
import ua.polodarb.ram.data.network.model.characters.CharacterNetworkModel
import ua.polodarb.ram.data.network.model.core.InfoNetworkModel
import javax.inject.Inject

class ApiServiceImpl @Inject constructor() : ApiService {

    private val client = KtorClient()

    override suspend fun getAllCharacters(
        page: Int,
        name: String?
    ): ResultOf<InfoNetworkModel<CharacterNetworkModel>> {
        return try {
            val urlBuilder = "${ApiRoutes.CHARACTERS}?page=$page"
            val finalUrl = if (!name.isNullOrEmpty()) "$urlBuilder&name=$name" else urlBuilder

            val response: InfoNetworkModel<CharacterNetworkModel> = client.getHttpClient.get {
                url(finalUrl)
            }.body()
            ResultOf.Success(response)
        } catch (e: Exception) {
            ResultOf.Error(e)
        }
    }
}
