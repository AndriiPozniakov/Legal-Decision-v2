package com.hazymoonstudio.legaldecision.network

import com.google.firebase.firestore.FirebaseFirestore
import com.hazymoonstudio.legaldecision.network.model.ArticleDto
import com.hazymoonstudio.legaldecision.temp.ArticleUserInfo
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CloudFireStoreService @Inject constructor(private val mDataBase: FirebaseFirestore) {
    suspend fun getArticlesList(): List<ArticleDto> {
        val articlesList: MutableList<ArticleDto> = ArrayList()
        val data = mDataBase.collection(UKRAINIAN_LAWS_TABLE).get().await()

        for(document in data){
            var temp = document.toObject(ArticleDto::class.java)
            temp.articleId = document.id

            articlesList.add(temp)
        }

        return articlesList
    }

    suspend fun getArticleListFromPositionWithLimit(start: Int, limit: Int): List<ArticleDto> {
        val articlesList: MutableList<ArticleDto> = ArrayList()
        val data = mDataBase.collection(UKRAINIAN_LAWS_TABLE).orderBy("title").startAfter(5).limit(5).get().await()
        //Log.i("ArticleDtosDataSource", "StartPos: $start, LoadSize: $limit")
        for(document in data){
            var temp = document.toObject(ArticleDto::class.java)
            temp.articleId = document.id

            articlesList.add(temp)
        }

        return articlesList
    }

    suspend fun getArticleDto(ArticleDtoId: String): ArticleDto {
        val data = mDataBase.collection(UKRAINIAN_LAWS_TABLE).document(ArticleDtoId).get().await()

        var article = ArticleDto()

        article.articleId = data.id
//        ArticleDto.title = data.getString("title").toString()
//        ArticleDto.text = HtmlCompat.fromHtml(data.getString("text").toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)

        return article
    }

    suspend fun updateUserInfoAboutArticleDto(userId: String, ArticleDtoId: String, ArticleDtoInfo: ArticleUserInfo) {
        mDataBase.collection(USERS_TABLE).document(userId).collection(USER_INFO_ABOUT_ArticleDto)
            .document(ArticleDtoId)
            .set(ArticleDtoInfo)
    }

    suspend fun getUserInfoAboutArticleDto(userId: String, ArticleDtoId: String): ArticleUserInfo {
        val data = mDataBase.collection(USERS_TABLE).document(userId).collection(
            USER_INFO_ABOUT_ArticleDto
        ).document(ArticleDtoId).get().await()
        val info = data.toObject(ArticleUserInfo::class.java)

        return info ?: ArticleUserInfo()
    }

    companion object {
        const val UKRAINIAN_LAWS_TABLE = "UkrainianLaws"
        const val USERS_TABLE = "Users"

        const val USER_INFO_ABOUT_ArticleDto = "UserInfo"
    }
}