package ua.polodarb.ram.data.network.model.locations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ua.polodarb.ram.data.network.model.base.NetworkBaseModel

@Serializable
data class LocationNetworkModel(
    @SerialName("type") val type: String,
    @SerialName("dimension") val dimension: String,
    @SerialName("residents") val residents: List<String>,
    @SerialName("id") override val id: Int,
    @SerialName("name") override val name: String,
    @SerialName("url") override val url: String,
    @SerialName("created") override val created: String
) : NetworkBaseModel()
