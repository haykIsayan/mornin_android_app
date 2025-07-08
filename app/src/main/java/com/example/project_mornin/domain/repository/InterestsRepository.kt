package com.example.project_mornin.domain.repository

import com.example.project_mornin.domain.entity.InterestsEntity

interface InterestsRepository {
    suspend fun getInterests(id: String): InterestsEntity

    suspend fun updateInterests(id: String, interests: InterestsEntity): Boolean

    suspend fun deleteInterests(id: String): Boolean

    suspend fun addInterests(interests: InterestsEntity): Boolean
}