package ua.polodarb.ram.data.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.polodarb.ram.data.database.AppDatabase
import ua.polodarb.ram.data.database.dao.CharactersDao
import ua.polodarb.ram.data.database.dao.RemoteKeyDao

@Module
@InstallIn(SingletonComponent::class)
class DatabaseDaoModule {

    @Provides
    fun bindCharactersDao(database: AppDatabase): CharactersDao = database.charactersDao()

    @Provides
    fun bindRemoteKeyDao(database: AppDatabase): RemoteKeyDao = database.remoteKeyDao()
}
