package ua.polodarb.ram.data.network.model.characters

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class GenderNetworkModel {
    @SerialName("Female") FEMALE,
    @SerialName("Male") MALE,
    @SerialName("Genderless") GENDERLESS,
    @SerialName("unknown") UNKNOWN
}
