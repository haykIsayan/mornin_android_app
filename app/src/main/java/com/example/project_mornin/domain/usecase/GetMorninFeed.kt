package com.example.project_mornin.domain.usecase

import com.example.project_mornin.domain.entity.MorninEntity
import com.example.project_mornin.domain.repository.InterestsRepository
import com.example.project_mornin.domain.repository.MorninRepository
import com.example.project_mornin.domain.repository.ProfileRepository

class GetMorninFeed(
    private val repository: MorninRepository,
    private val interestsRepository: InterestsRepository
) {
    suspend fun execute(): List<MorninEntity> {
        val interests = interestsRepository.getInterests("user1")
        val morninFeed = repository.getMorninFeed(interests)
        return morninFeed
    }
}