package com.example.project_mornin.data

import com.example.project_mornin.data.newsapi.ArticleMapper
import com.example.project_mornin.data.newsapi.NewsApiService
import com.example.project_mornin.domain.entity.InterestsEntity
import com.example.project_mornin.domain.entity.MorninEntity
import com.example.project_mornin.domain.entity.MorninTopic
import com.example.project_mornin.domain.repository.MorninRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class MorninDataSource(
    private val newsApiService: NewsApiService,
    private val articleMapper: ArticleMapper
): MorninRepository {

    companion object {
        private const val DEFAULT_PAGE_SIZE = 22
    }

    override suspend fun getMorninFeed(
        interests: InterestsEntity
    ): List<MorninEntity> = coroutineScope {
        val pageSize = DEFAULT_PAGE_SIZE / interests.topics.size
        val deferredResults = interests.topics.map { topic ->
            async {
                newsApiService.getTopHeadlines(
                    country = "us",
                    pageSize = pageSize,
                    category = topic.name.lowercase()
                ).articles
                    .filter {
                        it.urlToImage != null
                    }
                    .map {
                    articleMapper.mapToEntity(it, topic)
                }
            }
        }
        deferredResults.awaitAll().flatten().apply {
            shuffled()
        }
    }
}