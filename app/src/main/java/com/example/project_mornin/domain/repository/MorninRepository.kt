package com.example.project_mornin.domain.repository

import com.example.project_mornin.domain.entity.InterestsEntity
import com.example.project_mornin.domain.entity.MorninEntity

interface MorninRepository {
    suspend fun getMorninFeed(
        interests: InterestsEntity
    ): List<MorninEntity> // Example method to get Mornin feed
}