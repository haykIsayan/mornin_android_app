package com.example.project_mornin.data.newsapi

import com.example.project_mornin.domain.entity.MorninEntity
import com.example.project_mornin.domain.entity.MorninTopic

class ArticleMapper {
    fun mapToEntity(article: Article, topic: MorninTopic): MorninEntity {
        return object : MorninEntity {
            override val id: Int = 0
            override val title: String = article.title
            override val description: String = article.description ?: ""
            override val content: String = article.content ?: ""
            override val imageUrl: String = article.urlToImage ?: ""
            override val author: String = article.author ?: "Unknown"
            override val date: String = article.publishedAt
            override val topic: MorninTopic = topic
        }
    }
}