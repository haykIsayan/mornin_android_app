package com.example.project_mornin.di

import com.example.project_mornin.domain.usecase.GetMorninFeed
import com.example.project_mornin.domain.repository.InterestsRepository
import com.example.project_mornin.domain.repository.MorninRepository
import com.example.project_mornin.domain.repository.ProfileRepository
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
        ): GetMorninFeed {
        return GetMorninFeed(
            morninRepository,
            interestsRepository
        )
    }
}