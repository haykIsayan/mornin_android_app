package com.example.project_mornin.features.mornin

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.MovieCreation
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.project_mornin.domain.entity.MorninTopic


fun getMorninTopicIcon(topic: MorninTopic): ImageVector {
    return when (topic) {
        MorninTopic.TECHNOLOGY -> Icons.Filled.Computer
        MorninTopic.SCIENCE -> Icons.Filled.Science
        MorninTopic.HEALTH -> Icons.Filled.HealthAndSafety
        MorninTopic.FOOD -> Icons.Filled.Restaurant
        MorninTopic.ENTERTAINMENT -> Icons.Filled.Movie
        MorninTopic.TRAVEL -> Icons.Filled.Flight
        MorninTopic.ART -> Icons.Filled.Palette
        MorninTopic.GAMING -> Icons.Filled.SportsEsports
        MorninTopic.BUSINESS -> Icons.Filled.AttachMoney
        MorninTopic.POLITICS -> Icons.Filled.AccountBalance
        MorninTopic.SPORTS -> Icons.Filled.SportsSoccer
        MorninTopic.FASHION -> Icons.Filled.Checkroom
        MorninTopic.LIFESTYLE -> Icons.Filled.Spa
        else -> Icons.Filled.MoreHoriz
        // Add any additional topics as needed
    }
}