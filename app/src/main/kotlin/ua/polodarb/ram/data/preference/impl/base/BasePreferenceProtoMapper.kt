package ua.polodarb.ram.data.preference.impl.base

import ua.polodarb.ram.data.preference.base.BasePreferenceModel

interface BasePreferenceProtoMapper<Preference : BasePreferenceModel, Proto : Any> {

    fun Proto.toPreference(): Preference

    fun Preference.toProto(): Proto
}
