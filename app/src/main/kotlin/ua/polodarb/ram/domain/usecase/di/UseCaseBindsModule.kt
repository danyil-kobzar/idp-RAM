package ua.polodarb.ram.domain.usecase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.polodarb.ram.domain.usecase.characters.GetCharactersUseCase
import ua.polodarb.ram.domain.usecase.characters.SearchCharactersUseCase
import ua.polodarb.ram.domain.usecase.episodes.LoadEpisodesUseCase
import ua.polodarb.ram.domain.usecase.episodes.LoadSeasonsUseCase
import ua.polodarb.ram.domain.usecase.impl.characters.GetCharactersUseCaseImpl
import ua.polodarb.ram.domain.usecase.impl.characters.SearchCharactersUseCaseImpl
import ua.polodarb.ram.domain.usecase.impl.episodes.LoadEpisodesUseCaseImpl
import ua.polodarb.ram.domain.usecase.impl.episodes.LoadSeasonsUseCaseImpl
import ua.polodarb.ram.domain.usecase.impl.preferences.LoadGridColumnCountUseCaseImpl
import ua.polodarb.ram.domain.usecase.impl.preferences.SaveGridColumnCountUseCaseImpl
import ua.polodarb.ram.domain.usecase.preferences.LoadGridColumnCountUseCase
import ua.polodarb.ram.domain.usecase.preferences.SaveGridColumnCountUseCase

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseBindsModule {

    @Binds
    abstract fun bindGetCharactersUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase

    @Binds
    abstract fun bindSearchCharactersUseCase(useCase: SearchCharactersUseCaseImpl): SearchCharactersUseCase

    @Binds
    abstract fun bindEpisodesUseCase(useCase: LoadEpisodesUseCaseImpl): LoadEpisodesUseCase

    @Binds
    abstract fun bindSeasonsUseCase(useCase: LoadSeasonsUseCaseImpl): LoadSeasonsUseCase

    @Binds
    abstract fun bindSaveGridColumnCountUseCase(useCase: SaveGridColumnCountUseCaseImpl): SaveGridColumnCountUseCase

    @Binds
    abstract fun bindLoadGridColumnCountUseCase(useCase: LoadGridColumnCountUseCaseImpl): LoadGridColumnCountUseCase
}