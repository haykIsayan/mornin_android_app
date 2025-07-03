package com.example.project_mornin.domain.entity

interface MorninEntity {
    val id: Int
    val title: String
    val description: String
    val content: String
    val imageUrl: String
    val author: String
    val date: String
    val topic: MorninTopic
}