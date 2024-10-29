package ua.polodarb.ram.data.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.polodarb.ram.data.network.impl.source.NetworkDataSourceImpl
import ua.polodarb.ram.data.network.source.NetworkDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkBindsModule {

    @Binds
    abstract fun provideNetworkDataSource(source: NetworkDataSourceImpl): NetworkDataSource

}