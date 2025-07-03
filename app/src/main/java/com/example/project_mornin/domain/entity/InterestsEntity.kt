package com.example.project_mornin.domain.entity

interface InterestsEntity {
    val id: String
    val profileId: String
    val topics : List<MorninTopic>
}