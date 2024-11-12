package ua.polodarb.ram.data.database.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.polodarb.ram.data.database.AppDatabase
import ua.polodarb.ram.data.database.dao.CharactersDao
import ua.polodarb.ram.data.database.dao.EpisodesDao

@Module
@InstallIn(SingletonComponent::class)
class DatabaseDaoModule {

    @Provides
    fun bindCharactersDao(database: AppDatabase): CharactersDao = database.charactersDao()

    @Provides
    fun bindEpisodesDao(database: AppDatabase): EpisodesDao = database.episodesDao()

}
