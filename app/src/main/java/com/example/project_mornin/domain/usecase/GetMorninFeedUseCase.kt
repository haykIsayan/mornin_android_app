package com.example.project_mornin.domain.usecase

import com.example.project_mornin.domain.entity.InterestsEntity
import com.example.project_mornin.domain.entity.MorninEntity
import com.example.project_mornin.domain.repository.InterestsRepository
import com.example.project_mornin.domain.repository.MorninRepository

class GetMorninFeedUseCase(
    private val morninRepository: MorninRepository,
    private val interestsRepository: InterestsRepository
) {
    suspend fun execute(interests: InterestsEntity? = null): List<MorninEntity> {
        val userInterests = interests ?: interestsRepository.getInterests("user1")
        val morninFeed = morninRepository.getMorninFeed(userInterests)
        return morninFeed
    }
}