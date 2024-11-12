package ua.polodarb.ram.data.preference.impl.dao.base

import kotlinx.coroutines.flow.Flow

interface BasePreferenceDao<T> {

    fun subscribeToData(): Flow<T?>

    suspend fun updateData(value: T?)

    suspend fun getData(): T?
}
