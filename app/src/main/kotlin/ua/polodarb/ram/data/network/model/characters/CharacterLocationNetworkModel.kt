package ua.polodarb.ram.data.network.model.characters

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ua.polodarb.ram.data.network.model.base.BaseNetworkModel

@Serializable
data class CharacterLocationNetworkModel(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
) : BaseNetworkModel