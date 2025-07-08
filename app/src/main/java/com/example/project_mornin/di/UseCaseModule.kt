package com.example.project_mornin.di

import com.example.project_mornin.domain.usecase.GetMorninFeedUseCase
import com.example.project_mornin.domain.repository.InterestsRepository
import com.example.project_mornin.domain.repository.MorninRepository
import com.example.project_mornin.domain.repository.ProfileRepository
import com.example.project_mornin.domain.usecase.GetInterestsUseCase
import com.example.project_mornin.domain.usecase.GetMorninTopicsUseCase
import com.example.project_mornin.domain.usecase.UpdateInterestsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetMorninFeed(
        morninRepository: MorninRepository,
        profileRepository: ProfileRepository,
        interestsRepository: InterestsRepository
        ): GetMorninFeedUseCase {
        return GetMorninFeedUseCase(
            morninRepository,
            interestsRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetMorninTopicsUseCase(): GetMorninTopicsUseCase {
        return GetMorninTopicsUseCase()
    }

    @Provides
    @Singleton
    fun provideGetInterestsUseCase(
        interestsRepository: InterestsRepository
    ): GetInterestsUseCase {
        return GetInterestsUseCase(interestsRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateInterestsUseCase(
        interestsRepository: InterestsRepository
    ): UpdateInterestsUseCase {
        return UpdateInterestsUseCase(interestsRepository)
    }
}