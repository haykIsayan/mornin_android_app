package com.example.project_mornin.domain.entity


//business
//entertainment
//general
//health
//science
//sports
//technology


enum class MorninTopic {
    TECHNOLOGY,
    SCIENCE,
    HEALTH,
    BUSINESS,
    ENTERTAINMENT,
    SPORTS,
    POLITICS,
    ART,
    CULTURE,
    TRAVEL,
    FOOD,
    FASHION,
    GAMING,
    LIFESTYLE;

    companion object {

        fun getAvailableTopics(): List<MorninTopic> {
            return entries.filter {
                it == ENTERTAINMENT ||
                        it == TECHNOLOGY ||
                        it == SCIENCE ||
                        it == HEALTH ||
                        it == BUSINESS ||
                        it == SPORTS
            }
        }

        fun fromString(topic: String): MorninTopic {
            return entries.firstOrNull { it.name.equals(topic, ignoreCase = true) }
                ?: throw IllegalArgumentException("Unknown topic: $topic")
        }
    }
}