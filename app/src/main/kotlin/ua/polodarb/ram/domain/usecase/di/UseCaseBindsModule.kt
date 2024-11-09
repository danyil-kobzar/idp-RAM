package ua.polodarb.ram.domain.usecase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.polodarb.ram.domain.usecase.characters.GetCharactersUseCase
import ua.polodarb.ram.domain.usecase.characters.SearchCharactersUseCase
import ua.polodarb.ram.domain.usecase.impl.GetCharactersUseCaseImpl
import ua.polodarb.ram.domain.usecase.impl.SearchCharactersUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseBindsModule {

    @Binds
    abstract fun bindGetCharactersUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase

    @Binds
    abstract fun bindSearchCharactersUseCase(useCase: SearchCharactersUseCaseImpl): SearchCharactersUseCase

}