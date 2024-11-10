package ua.polodarb.ram.data.database.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.polodarb.ram.data.database.source.CharactersDbSource
import ua.polodarb.ram.data.database.source.EpisodesDbSource
import ua.polodarb.ram.data.database.source.impl.CharactersDbSourceImpl
import ua.polodarb.ram.data.database.source.impl.EpisodesDbSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DbSourceBindModule {

    @Binds
    abstract fun provideCharactersDbSource(source: CharactersDbSourceImpl): CharactersDbSource

    @Binds
    abstract fun provideEpisodesDbSource(source: EpisodesDbSourceImpl): EpisodesDbSource

}