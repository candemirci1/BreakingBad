package com.example.breakingbad.di

import com.example.breakingbad.data.repository.CharacterRepositoryImpl
import com.example.breakingbad.data.repository.QuoteRepositoryImpl
import com.example.breakingbad.data.service.BreakingBadService
import com.example.breakingbad.domain.repository.CharactersRepository
import com.example.breakingbad.domain.repository.QuoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(service: BreakingBadService): CharactersRepository {
        return CharacterRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideQuoteRepository(service: BreakingBadService): QuoteRepository {
        return QuoteRepositoryImpl(service)
    }

}