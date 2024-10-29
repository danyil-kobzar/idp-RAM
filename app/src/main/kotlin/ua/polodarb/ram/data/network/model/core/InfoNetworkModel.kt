package ua.polodarb.ram.data.network.model.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoNetworkModel<T>(
    @SerialName("info") val info: PageInfoNetworkModel? = null,
    @SerialName("results") val results: List<T>? = null
)
