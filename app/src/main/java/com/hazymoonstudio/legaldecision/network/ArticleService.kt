package com.hazymoonstudio.legaldecision.network

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.hazymoonstudio.legaldecision.network.model.ArticleDto
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ArticleService @Inject constructor(private val mDataBase: FirebaseFirestore) {
    suspend fun getArticle(id: String): ArticleDto {
        val data = mDataBase.collection(UKRAINIAN_LAWS_TABLE).document(id).get().await()
        val article = ArticleDto()
        article.articleId = data.id
        return article
    }

    suspend fun getFirstArticles(loadSize: Int): List<ArticleDto> {
        return getArticlesFromQuery(data = getFirstArticlesQuery(loadSize = loadSize))
    }

    suspend fun getNextArticles(loadSize: Int, lastArticleId: String): List<ArticleDto> {
        return getArticlesFromQuery(data = getNextArticlesQuery(loadSize = loadSize, lastArticleId = lastArticleId))
    }

    private fun getArticlesFromQuery(data: QuerySnapshot): List<ArticleDto> {
        val articlesList: MutableList<ArticleDto> = ArrayList()

        for(document in data){
            val temp = document.toObject(ArticleDto::class.java)
            temp.articleId = document.id

            articlesList.add(temp)
        }

        return articlesList
    }

    private suspend fun getFirstArticlesQuery(loadSize: Int): QuerySnapshot {
        return mDataBase.collection(UKRAINIAN_LAWS_TABLE)
                .orderBy(FieldPath.documentId())
                .limit(loadSize.toLong())
                .get()
                .await()
    }

    private suspend fun getNextArticlesQuery(loadSize: Int, lastArticleId: String): QuerySnapshot {
        return mDataBase.collection(UKRAINIAN_LAWS_TABLE)
                .orderBy(FieldPath.documentId())
                .startAfter(lastArticleId)
                .limit(loadSize.toLong())
                .get()
                .await()
    }

//    suspend fun getArticleListFromPositionWithLimit(start: Int, limit: Int): List<ArticleDto> {
//        val articlesList: MutableList<ArticleDto> = ArrayList()
//        val data = mDataBase.collection(UKRAINIAN_LAWS_TABLE).orderBy("title").startAfter(5).limit(5).get().await()
//        //Log.i("ArticleDtosDataSource", "StartPos: $start, LoadSize: $limit")
//        for(document in data){
//            var temp = document.toObject(ArticleDto::class.java)
//            temp.articleId = document.id
//
//            articlesList.add(temp)
//        }
//
//        return articlesList
//    }
//
//    suspend fun updateUserInfoAboutArticleDto(userId: String, ArticleDtoId: String, ArticleDtoInfo: ArticleUserInfo) {
//        mDataBase.collection(USERS_TABLE).document(userId).collection(USER_INFO_ABOUT_ArticleDto)
//            .document(ArticleDtoId)
//            .set(ArticleDtoInfo)
//    }
//
//    suspend fun getUserInfoAboutArticleDto(userId: String, ArticleDtoId: String): ArticleUserInfo {
//        val data = mDataBase.collection(USERS_TABLE).document(userId).collection(
//            USER_INFO_ABOUT_ArticleDto
//        ).document(ArticleDtoId).get().await()
//        val info = data.toObject(ArticleUserInfo::class.java)
//
//        return info ?: ArticleUserInfo()
//    }

    companion object {
        const val UKRAINIAN_LAWS_TABLE = "UkrainianLaws"
        const val USERS_TABLE = "Users"

        const val USER_INFO_ABOUT_ARTICLE = "UserInfo"

        //Fields
        const val ARTICLE_TABLE_DATE_FIELD = "title"
    }
}