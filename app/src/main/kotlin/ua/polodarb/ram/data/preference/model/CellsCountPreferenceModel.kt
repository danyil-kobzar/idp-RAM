package ua.polodarb.ram.data.preference.model

import kotlinx.serialization.Serializable
import ua.polodarb.ram.data.preference.base.BasePreferenceModel

@Serializable
data class CellsCountPreferenceModel(
    val cellsCount: Int
) : BasePreferenceModel
