package com.example.project_mornin.data.dto

import com.example.project_mornin.domain.entity.InterestsEntity
import com.example.project_mornin.domain.entity.MorninTopic

data class InterestsDto(
    override val id: String, override val profileId: String, override val topics: List<MorninTopic>

): InterestsEntity {
    override fun withTopics(topics: List<MorninTopic>): InterestsEntity {
        return copy(topics = topics)
    }
}
