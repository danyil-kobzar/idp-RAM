package ua.polodarb.ram.data.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import ua.polodarb.ram.data.network.api.ApiService
import ua.polodarb.ram.data.network.impl.api.ApiServiceImpl

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiService(): ApiService {
        return ApiServiceImpl()
    }

}