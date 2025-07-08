package com.example.project_mornin.domain.usecase

import com.example.project_mornin.domain.entity.MorninTopic
import kotlinx.coroutines.delay

class GetMorninTopicsUseCase {
    suspend fun execute(): List<MorninTopic> {
        delay(500)
        return MorninTopic.getAvailableTopics()
    }
}