package ua.polodarb.ram.data.network.model.characters

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class StatusNetworkModel {
    @SerialName("Alive") ALIVE,
    @SerialName("Dead") DEAD,
    @SerialName("unknown") UNKNOWN
}
