package com.example.project_mornin.domain.entity

enum class MorninTopic {
    TECHNOLOGY,
    SCIENCE,
    HEALTH,
    BUSINESS,
    ENTERTAINMENT,
    SPORTS,
    WORLD,
    POLITICS,
    ART,
    CULTURE,
    TRAVEL,
    FOOD,
    FASHION,
    GAMING,
    LIFESTYLE;

    companion object {
        fun fromString(topic: String): MorninTopic {
            return entries.firstOrNull { it.name.equals(topic, ignoreCase = true) }
                ?: throw IllegalArgumentException("Unknown topic: $topic")
        }
    }
}