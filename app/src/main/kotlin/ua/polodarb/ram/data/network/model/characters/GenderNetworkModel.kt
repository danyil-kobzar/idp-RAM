package ua.polodarb.ram.data.network.model.characters

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ua.polodarb.ram.data.network.model.base.BaseNetworkModel

@Serializable
enum class GenderNetworkModel : BaseNetworkModel {
    @SerialName("Female")
    FEMALE,
    @SerialName("Male")
    MALE,
    @SerialName("Genderless")
    GENDERLESS,
    @SerialName("unknown")
    UNKNOWN
}