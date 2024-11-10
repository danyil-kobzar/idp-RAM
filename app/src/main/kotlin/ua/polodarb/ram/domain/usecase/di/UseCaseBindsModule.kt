package ua.polodarb.ram.domain.usecase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.polodarb.ram.domain.usecase.characters.GetCharactersUseCase
import ua.polodarb.ram.domain.usecase.characters.SearchCharactersUseCase
import ua.polodarb.ram.domain.usecase.episodes.GetEpisodesUseCase
import ua.polodarb.ram.domain.usecase.impl.characters.GetCharactersUseCaseImpl
import ua.polodarb.ram.domain.usecase.impl.characters.SearchCharactersUseCaseImpl
import ua.polodarb.ram.domain.usecase.impl.episodes.GetEpisodesUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseBindsModule {

    @Binds
    abstract fun bindGetCharactersUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase

    @Binds
    abstract fun bindSearchCharactersUseCase(useCase: SearchCharactersUseCaseImpl): SearchCharactersUseCase

    @Binds
    abstract fun bindEpisodesUseCase(useCase: GetEpisodesUseCaseImpl): GetEpisodesUseCase

}