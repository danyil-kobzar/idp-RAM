package ua.polodarb.ram.domain.paging.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MediatorModule {

    @Provides
    fun provideSearchByName(): String {
        return ""
    }
}
