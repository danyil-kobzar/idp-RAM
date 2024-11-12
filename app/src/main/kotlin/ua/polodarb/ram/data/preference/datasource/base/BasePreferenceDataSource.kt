package ua.polodarb.ram.data.preference.datasource.base

import kotlinx.coroutines.flow.Flow
import ua.polodarb.ram.data.preference.base.BasePreferenceModel

interface BasePreferenceDataSource<T : BasePreferenceModel> {

    fun subscribeToData(): Flow<T?>

    suspend fun updateData(value: T?)

    suspend fun getData(): T?
}
