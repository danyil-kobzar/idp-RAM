package ua.polodarb.ram.data.preference.impl.dao

import android.util.Log
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import ua.polodarb.ram.data.preference.impl.dao.base.BasePreferenceDao
import ua.polodarb.ram.data.preference.model.CellsCountProtoModel
import java.io.IOException
import javax.inject.Inject

class CellsCountPreferenceDao @Inject constructor(
    private val preference: DataStore<CellsCountProtoModel>
) : BasePreferenceDao<CellsCountProtoModel> {

    override fun subscribeToData(): Flow<CellsCountProtoModel?> =
        preference.data.catch { exception ->
            if (exception is IOException) {
                Log.e("CellsCountPreferenceDao", "Error reading preferences.", exception)
                emit(CellsCountProtoModel.getDefaultInstance())
            } else {
                throw exception
            }
        }

    override suspend fun getData(): CellsCountProtoModel? = preference.data.firstOrNull()

    override suspend fun updateData(value: CellsCountProtoModel?) {
        preference.updateData { currentData ->
            currentData.toBuilder()
                .setCellsCount(value?.cellsCount ?: 1)
                .build()
        }
    }
}