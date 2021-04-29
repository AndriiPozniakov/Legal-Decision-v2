package com.hazymoonstudio.legaldecision.presentation.ui.articles_list

import androidx.paging.PositionalDataSource
import com.hazymoonstudio.legaldecision.domain.model.Article
import com.hazymoonstudio.legaldecision.repository.ArticleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ArticlesDataSource(private val repository: ArticleRepository, private val scope: CoroutineScope) : PositionalDataSource<Article>() {
    private var mTotalCount: Int = 0

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Article>) {
        executeQuery(params.requestedLoadSize) {
            callback.onResult(it, mTotalCount)
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Article>) {
        executeQuery(params.loadSize) {
            callback.onResult(it)
        }
    }

    private fun executeQuery(loadSize: Int, callback:(List<Article>) -> Unit) {
        scope.launch() {
            val articles = repository.getArticlesList(loadSize)
            mTotalCount += articles.size
            callback(articles)
        }
    }
}