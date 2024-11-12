package ua.polodarb.ram.data.preference.impl.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ua.polodarb.ram.data.preference.impl.serializer.CellsCountSerializer
import ua.polodarb.ram.data.preference.model.CellsCountProtoModel

@Module
@InstallIn(SingletonComponent::class)
class PreferenceDaoModule {

    @Provides
    fun provideStartConfigurePreferenceDao(
        @ApplicationContext applicationContext: Context,
        startConfigureSerializer: CellsCountSerializer,
    ): DataStore<CellsCountProtoModel> =
        DataStoreFactory.create(
            serializer = startConfigureSerializer,
        ) {
            applicationContext.dataStoreFile("cellscount.pb")
        }
}
