package com.example.project_mornin.domain.usecase

import com.example.project_mornin.domain.entity.InterestsEntity
import com.example.project_mornin.domain.repository.InterestsRepository

class GetInterestsUseCase(
    private val interestsRepository: InterestsRepository
) {

    suspend fun execute(): InterestsEntity {
        return interestsRepository.getInterests("user1")
    }
}