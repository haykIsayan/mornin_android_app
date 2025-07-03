package com.example.project_mornin.di

import com.example.project_mornin.data.InterestsDataTemp
import com.example.project_mornin.data.MorninDataSource
import com.example.project_mornin.data.ProfileDataTemp
import com.example.project_mornin.data.newsapi.ArticleMapper
import com.example.project_mornin.data.newsapi.NewsApiService
import com.example.project_mornin.domain.repository.InterestsRepository
import com.example.project_mornin.domain.repository.MorninRepository
import com.example.project_mornin.domain.repository.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import okhttp3.OkHttpClient
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsApiService(): NewsApiService {

        val client = OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("User-Agent", "MorninApp/Android")
                .build()
            chain.proceed(newRequest)
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(): ProfileRepository {
        return ProfileDataTemp()
    }

    @Provides
    @Singleton
    fun provideInterestsRepository(): InterestsRepository {
        return InterestsDataTemp()
    }

    @Provides
    @Singleton
    fun provideMorninRepository(
        newsApiService: NewsApiService,
    ): MorninRepository {
        return MorninDataSource(
            newsApiService = newsApiService,
            articleMapper = ArticleMapper()
        )
    }
}