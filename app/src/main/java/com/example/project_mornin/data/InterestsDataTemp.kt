package com.example.project_mornin.data

import com.example.project_mornin.data.dto.InterestsDto
import com.example.project_mornin.domain.repository.InterestsRepository
import com.example.project_mornin.domain.entity.InterestsEntity
import com.example.project_mornin.domain.entity.MorninTopic
import kotlinx.coroutines.delay

class InterestsDataTemp : InterestsRepository {

    companion object {
        // Static mutable map to store interests data
        private val interestsMap = mutableMapOf<String, InterestsEntity>().apply {
            // Pre-populate with some dummy data
            put("user1", InterestsDto(
                id = "user1",
                profileId = "user1",
                topics = listOf(
                    MorninTopic.BUSINESS,
                    MorninTopic.ENTERTAINMENT,
                    MorninTopic.TECHNOLOGY
                )
            ))
        }
    }

    override suspend fun getInterests(id: String): InterestsEntity {
        delay(750)
        return interestsMap[id] ?: throw NoSuchElementException("No interests found for id: $id")
    }

    override suspend fun updateInterests(id: String, interests: InterestsEntity): Boolean {
        return if (interestsMap.containsKey(id)) {
            interestsMap[id] = interests
            true
        } else {
            false
        }
    }

    override suspend fun deleteInterests(id: String): Boolean {
        return interestsMap.remove(id) != null
    }

    override suspend fun addInterests(interests: InterestsEntity): Boolean {
        val id = interests.id
        return if (!interestsMap.containsKey(id)) {
            interestsMap[id] = interests
            true
        } else {
            false
        }
    }
}