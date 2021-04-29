package com.hazymoonstudio.legaldecision.repository

import com.hazymoonstudio.legaldecision.domain.model.Article
import com.hazymoonstudio.legaldecision.network.ArticleService
import com.hazymoonstudio.legaldecision.network.model.ArticleDtoMapper

class ArticleRepository_Impl (
    private val articleService: ArticleService,
    private val mapper: ArticleDtoMapper)
: ArticleRepository{
    
    override suspend fun getArticlesList(loadSize: Int): List<Article> {
        return mapper.toDomainList(articleService.getArticlesList(loadSize))
    }

    override suspend fun getArticle(articleId: String): Article {
        return mapper.mapToDomainModel(articleService.getArticle(articleId))
    }
}