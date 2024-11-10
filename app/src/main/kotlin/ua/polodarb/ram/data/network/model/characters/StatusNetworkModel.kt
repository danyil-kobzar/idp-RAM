package ua.polodarb.ram.data.network.model.characters

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ua.polodarb.ram.data.network.model.base.BaseNetworkModel

@Serializable
enum class StatusNetworkModel : BaseNetworkModel {
    @SerialName("Alive")
    ALIVE,
    @SerialName("Dead")
    DEAD,
    @SerialName("unknown")
    UNKNOWN
}
