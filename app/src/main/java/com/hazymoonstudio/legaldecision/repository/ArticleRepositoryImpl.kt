package com.hazymoonstudio.legaldecision.repository

import com.hazymoonstudio.legaldecision.domain.model.Article
import com.hazymoonstudio.legaldecision.network.ArticleService
import com.hazymoonstudio.legaldecision.network.model.ArticleDtoMapper

class ArticleRepositoryImpl (
    private val articleService: ArticleService,
    private val mapper: ArticleDtoMapper)
: ArticleRepository{

    override suspend fun getArticle(articleId: String): Article {
        return mapper.mapToDomainModel(articleService.getArticle(id = articleId))
    }

    override suspend fun getFirstArticles(loadSize: Int): List<Article> {
        return mapper.toDomainList(articleService.getFirstArticles(loadSize = loadSize))
    }

    override suspend fun getNextArticles(loadSize: Int, lastArticleId: String): List<Article> {
        return mapper.toDomainList(articleService.getNextArticles(loadSize = loadSize, lastArticleId = lastArticleId))
    }
}