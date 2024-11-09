package ua.polodarb.ram.data.network.model.episodes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ua.polodarb.ram.data.network.model.base.BaseNetworkModel

@Serializable
data class EpisodeNetworkModel(
    @SerialName("air_date") val airDate: String,
    @SerialName("episode") val episode: String,
    @SerialName("characters") val characters: List<String>,
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
    @SerialName("created") val created: String
) : BaseNetworkModel