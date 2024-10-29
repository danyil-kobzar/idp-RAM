package ua.polodarb.ram.data.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.polodarb.ram.data.network.impl.source.NetworkDataSourceImpl
import ua.polodarb.ram.data.network.source.NetworkDataSource
import ua.polodarb.ram.data.repository.CharactersRepository
import ua.polodarb.ram.data.repository.impl.CharactersRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindsModule {

    @Binds
    abstract fun provideCharactersRepository(repository: CharactersRepositoryImpl): CharactersRepository

}