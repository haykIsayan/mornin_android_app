package com.example.project_mornin.features.mornin.interests

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project_mornin.domain.entity.MorninTopic

@Composable
fun InterestsGrid(
    interestsUiData: InterestsUiData,
    onTopicSelected: (MorninTopic) -> Unit
) {
    val interests = interestsUiData.interests
    val availableTopics = interestsUiData.availableTopics

    LazyHorizontalStaggeredGrid(
        rows = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth().height(100.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(availableTopics) { topic ->
            InterestItem(
                topic = topic,
                isSelected = isSelected(topic, interests.topics),
                onTopicSelected = onTopicSelected
            )
        }
    }
}

@Composable
fun InterestItem(
    topic: MorninTopic,
    isSelected: Boolean,
    onTopicSelected: (MorninTopic) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) Color.White else Color.Black.copy(alpha = 0.3f))
            .clickable { onTopicSelected(topic) }
    ) {
        Text(
            text = topic.toString(),
            modifier = Modifier.padding(vertical = 1.dp, horizontal = 8.dp),
            fontSize = 8.sp,
            maxLines = 1,
            color = if (isSelected) Color.Black else Color.White,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis
        )
    }
}

fun isSelected(topic: MorninTopic, interestTopics: List<MorninTopic>): Boolean {
    return interestTopics.contains(topic)
}