package ua.polodarb.ram.data.network.model.characters

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterLocationNetworkModel(
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)
