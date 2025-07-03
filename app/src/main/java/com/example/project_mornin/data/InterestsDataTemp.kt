package com.example.project_mornin.data

import com.example.project_mornin.domain.repository.InterestsRepository
import com.example.project_mornin.domain.entity.InterestsEntity
import com.example.project_mornin.domain.entity.MorninTopic

class InterestsDataTemp : InterestsRepository {

    companion object {
        // Static mutable map to store interests data
        private val interestsMap = mutableMapOf<String, InterestsEntity>().apply {
            // Pre-populate with some dummy data
            put("user1", object : InterestsEntity {
                override val id: String = "user1"
                override val profileId: String = "user1"
                override val topics: List<MorninTopic> = listOf(
                    MorninTopic.BUSINESS,
                    MorninTopic.ENTERTAINMENT,
                    MorninTopic.TECHNOLOGY

                )
            })
        }
    }

    override fun getInterests(id: String): InterestsEntity {
        return interestsMap[id] ?: throw NoSuchElementException("No interests found for id: $id")
    }

    override fun updateInterests(id: String, interests: InterestsEntity): Boolean {
        return if (interestsMap.containsKey(id)) {
            interestsMap[id] = interests
            true
        } else {
            false
        }
    }

    override fun deleteInterests(id: String): Boolean {
        return interestsMap.remove(id) != null
    }

    override fun addInterests(interests: InterestsEntity): Boolean {
        val id = interests.id
        return if (!interestsMap.containsKey(id)) {
            interestsMap[id] = interests
            true
        } else {
            false
        }
    }
}