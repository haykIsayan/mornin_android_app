package com.example.project_mornin.domain.usecase

import com.example.project_mornin.domain.entity.InterestsEntity
import com.example.project_mornin.domain.repository.InterestsRepository
import javax.inject.Inject

class UpdateInterestsUseCase(
    private val interestsRepository: InterestsRepository
) {
    /**
     * Updates the user's interests in the repository
     *
     * @param interests The updated interests to be saved
     * @return The saved interests entity after the update
     */
    suspend fun execute(interests: InterestsEntity): Boolean {
        return interestsRepository.updateInterests("user1", interests)
    }
}