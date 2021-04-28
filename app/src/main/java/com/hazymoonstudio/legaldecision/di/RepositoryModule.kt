package com.hazymoonstudio.legaldecision.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hazymoonstudio.legaldecision.network.ArticleService
import com.hazymoonstudio.legaldecision.network.model.ArticleDtoMapper
import com.hazymoonstudio.legaldecision.repository.ArticleRepository
import com.hazymoonstudio.legaldecision.repository.ArticleRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesFirebaseFireStore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun providesFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun providesArticleRepository(fireStore: FirebaseFirestore): ArticleService = ArticleService(mDataBase = fireStore)

    @Singleton
    @Provides
    fun providesArticleRepository(articleService: ArticleService, articleMapper: ArticleDtoMapper):
            ArticleRepository {
        return ArticleRepository_Impl(
            articleService = articleService,
            mapper = articleMapper
        )
    }
}