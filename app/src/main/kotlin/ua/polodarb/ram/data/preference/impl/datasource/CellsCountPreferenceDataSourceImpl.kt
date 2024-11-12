package ua.polodarb.ram.data.preference.impl.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ua.polodarb.ram.data.preference.datasource.CellsCountPreferenceDataSource
import ua.polodarb.ram.data.preference.impl.base.BasePreferenceProtoMapper
import ua.polodarb.ram.data.preference.impl.dao.CellsCountPreferenceDao
import ua.polodarb.ram.data.preference.model.CellsCountPreferenceModel
import ua.polodarb.ram.data.preference.model.CellsCountProtoModel
import javax.inject.Inject

class CellsCountPreferenceDataSourceImpl @Inject constructor(
    private val cellsCountPreferenceDao: CellsCountPreferenceDao
) : CellsCountPreferenceDataSource {

    override fun subscribeToData(): Flow<CellsCountPreferenceModel?> =
        cellsCountPreferenceDao.subscribeToData().map { protoModel ->
            protoModel?.toPreference()
        }

    override suspend fun updateData(value: CellsCountPreferenceModel?) {
        cellsCountPreferenceDao.updateData(value?.toProto())
    }

    override suspend fun getData(): CellsCountPreferenceModel? =
        cellsCountPreferenceDao.getData()?.toPreference()

    companion object : BasePreferenceProtoMapper<CellsCountPreferenceModel, CellsCountProtoModel> {
        override fun CellsCountProtoModel.toPreference(): CellsCountPreferenceModel =
            CellsCountPreferenceModel(cellsCount = this.cellsCount)

        override fun CellsCountPreferenceModel.toProto(): CellsCountProtoModel =
            CellsCountProtoModel.newBuilder()
                .setCellsCount(cellsCount)
                .build()
    }
}