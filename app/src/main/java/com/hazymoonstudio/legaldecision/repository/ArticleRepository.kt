package com.hazymoonstudio.legaldecision.repository

import com.google.firebase.firestore.DocumentSnapshot
import com.hazymoonstudio.legaldecision.domain.model.Article

interface ArticleRepository {
    suspend fun getArticle(articleId: String): Article
    suspend fun getFirstArticles(loadSize: Int): List<Article>
    suspend fun getNextArticles(loadSize: Int, lastArticleId: String): List<Article>

//    suspend fun updateUserInfoAboutArticle(userId: String, articleId: String, articleInfo: ArticleUserInfo)
//    suspend fun getUserInfoAboutArticle(userId: String, articleId: String): ArticleUserInfo
//
//    suspend fun getArticleListFromPositionWithLimit(start: Int, limit: Int): List<Article>
}