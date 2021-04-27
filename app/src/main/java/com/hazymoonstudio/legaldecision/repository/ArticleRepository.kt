package com.hazymoonstudio.legaldecision.repository

import com.hazymoonstudio.legaldecision.network.model.Article
import com.hazymoonstudio.legaldecision.temp.ArticleUserInfo

interface ArticleRepositroy {
    suspend fun getArticlesList(): List<Article>
    suspend fun getArticle(articleId: String): Article
    suspend fun updateUserInfoAboutArticle(userId: String, articleId: String, articleInfo: ArticleUserInfo)
    suspend fun getUserInfoAboutArticle(userId: String, articleId: String): ArticleUserInfo

    suspend fun getArticleListFromPositionWithLimit(start: Int, limit: Int): List<Article>
}