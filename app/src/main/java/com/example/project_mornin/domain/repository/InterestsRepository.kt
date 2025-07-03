package com.example.project_mornin.domain.repository

import com.example.project_mornin.domain.entity.InterestsEntity

interface InterestsRepository {
    fun getInterests(id: String): InterestsEntity

    fun updateInterests(id: String, interests: InterestsEntity): Boolean

    fun deleteInterests(id: String): Boolean

    fun addInterests(interests: InterestsEntity): Boolean
}