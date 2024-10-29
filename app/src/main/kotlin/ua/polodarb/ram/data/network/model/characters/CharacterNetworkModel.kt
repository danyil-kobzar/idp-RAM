package ua.polodarb.ram.data.network.model.characters

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ua.polodarb.ram.data.repository.models.characters.GenderRepoModel
import ua.polodarb.ram.data.repository.models.characters.StatusRepoModel

@Serializable
data class CharacterNetworkModel(
    @SerialName("status") val status: StatusNetworkModel,
    @SerialName("species") val species: String,
    @SerialName("type") val type: String,
    @SerialName("gender") val gender: GenderNetworkModel,
    @SerialName("origin") val origin: CharacterLocationNetworkModel,
    @SerialName("location") val location: CharacterLocationNetworkModel,
    @SerialName("image") val image: String,
    @SerialName("episode") val episode: List<String>,
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
    @SerialName("created") val created: String
)
