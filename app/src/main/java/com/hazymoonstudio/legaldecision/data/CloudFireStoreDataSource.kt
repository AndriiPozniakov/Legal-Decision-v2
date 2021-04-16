package com.hazymoonstudio.legaldecision.data

import android.text.Html
import androidx.core.text.HtmlCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.hazymoonstudio.legaldecision.models.Article
import com.hazymoonstudio.legaldecision.models.ArticleUserInfo
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CloudFireStoreDataSource @Inject constructor(private val mDataBase: FirebaseFirestore): RepositoryDataSource {
    override suspend fun getArticlesList(): List<Article> {
        val articlesList: MutableList<Article> = ArrayList()
        val data = mDataBase.collection(UKRAINIAN_LAWS_TABLE).get().await()

        for(document in data){
            var temp = document.toObject(Article::class.java)
            temp.articleId = document.id

            articlesList.add(temp)
        }

        return articlesList
    }

    override suspend fun getArticleListFromPositionWithLimit(start: Int, limit: Int): List<Article> {
        val articlesList: MutableList<Article> = ArrayList()
        val data = mDataBase.collection(UKRAINIAN_LAWS_TABLE).orderBy("title").startAfter(5).limit(5).get().await()
        //Log.i("ArticlesDataSource", "StartPos: $start, LoadSize: $limit")
        for(document in data){
            var temp = document.toObject(Article::class.java)
            temp.articleId = document.id

            articlesList.add(temp)
        }

        return articlesList
    }

    override suspend fun getArticle(articleId: String): Article {
        val data = mDataBase.collection(UKRAINIAN_LAWS_TABLE).document(articleId).get().await()

        var article = Article()

        article.articleId = data.id
        article.title = data.getString("title").toString()
        article.text = HtmlCompat.fromHtml(data.getString("text").toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)

        return article
    }

    override suspend fun updateUserInfoAboutArticle(userId: String, articleId: String, articleInfo: ArticleUserInfo) {
        mDataBase.collection(USERS_TABLE).document(userId).collection(USER_INFO_ABOUT_ARTICLE)
            .document(articleId)
            .set(articleInfo)
    }

    override suspend fun getUserInfoAboutArticle(userId: String, articleId: String): ArticleUserInfo {
        val data = mDataBase.collection(USERS_TABLE).document(userId).collection(USER_INFO_ABOUT_ARTICLE).document(articleId).get().await()
        val info = data.toObject(ArticleUserInfo::class.java)

        return info ?: ArticleUserInfo()
    }

    companion object {
        const val UKRAINIAN_LAWS_TABLE = "UkrainianLaws"
        const val USERS_TABLE = "Users"

        const val USER_INFO_ABOUT_ARTICLE = "UserInfo"
    }
}