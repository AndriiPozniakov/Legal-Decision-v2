package com.hazymoonstudio.legaldecision.data

import com.hazymoonstudio.legaldecision.models.Article
import com.hazymoonstudio.legaldecision.models.ArticleUserInfo

interface RepositoryDataSource {
    suspend fun getArticlesList(): List<Article>
    suspend fun getArticle(articleId: String): Article
    suspend fun updateUserInfoAboutArticle(userId: String, articleId: String, articleInfo: ArticleUserInfo)
    suspend fun getUserInfoAboutArticle(userId: String, articleId: String): ArticleUserInfo

    suspend fun getArticleListFromPositionWithLimit(start: Int, limit: Int): List<Article>
}