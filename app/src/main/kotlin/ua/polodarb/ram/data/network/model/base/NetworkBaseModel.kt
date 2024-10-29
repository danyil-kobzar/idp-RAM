package ua.polodarb.ram.data.network.model.base

import kotlinx.serialization.Serializable

@Serializable
abstract class NetworkBaseModel {
    abstract val id: Int
    abstract val name: String
    abstract val url: String
    abstract val created: String
}
