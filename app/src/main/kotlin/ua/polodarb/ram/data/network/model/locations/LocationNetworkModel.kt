package ua.polodarb.ram.data.network.model.locations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ua.polodarb.ram.data.network.model.base.BaseNetworkModel

@Serializable
data class LocationNetworkModel(
    @SerialName("type") val type: String,
    @SerialName("dimension") val dimension: String,
    @SerialName("residents") val residents: List<String>,
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("url") val url: String,
    @SerialName("created") val created: String
) : BaseNetworkModel
