package ua.polodarb.ram.data.preference.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.polodarb.ram.data.preference.datasource.CellsCountPreferenceDataSource
import ua.polodarb.ram.data.preference.impl.datasource.CellsCountPreferenceDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindCellsCountPreferenceDataSource(
        cellsCountPreferenceDataSourceImpl: CellsCountPreferenceDataSourceImpl,
    ): CellsCountPreferenceDataSource

}
