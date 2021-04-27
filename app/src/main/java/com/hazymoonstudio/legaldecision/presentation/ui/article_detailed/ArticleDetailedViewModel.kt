package com.hazymoonstudio.legaldecision.presentation.article_detailed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.hazymoonstudio.legaldecision.data.RepositoryDataSource
import com.hazymoonstudio.legaldecision.models.Article
import com.hazymoonstudio.legaldecision.models.ArticleUserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailedViewModel @Inject constructor(private val mDataSource: RepositoryDataSource, private val mAuth: FirebaseAuth) : ViewModel() {
    private val mArticle: MutableLiveData<Article> = MutableLiveData()
    private val mArticleUserInfo: MutableLiveData<ArticleUserInfo> = MutableLiveData()

    fun getArticle(articleId: String): LiveData<Article> {
        viewModelScope.launch {
            mArticle.value = mDataSource.getArticle(articleId)
        }

        return mArticle
    }

    fun getArticleUserInfo(articleId: String): LiveData<ArticleUserInfo>{
        val user = mAuth.currentUser

        if(user != null) {
            val userUid = user.uid
            viewModelScope.launch {
                mArticleUserInfo.value = mDataSource.getUserInfoAboutArticle(userUid, articleId)
            }
        }

        return mArticleUserInfo
    }


    fun updateUserInfoAboutArticle(articleId: String, documentInfo: ArticleUserInfo) {
        val user = mAuth.currentUser

        if(user != null) {
            val userUid = user.uid
            viewModelScope.launch {
                mDataSource.updateUserInfoAboutArticle(userUid, articleId, documentInfo)
            }
        }
    }

}