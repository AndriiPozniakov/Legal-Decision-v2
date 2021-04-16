package com.hazymoonstudio.legaldecision.ui.articles_list

import android.util.Log
import androidx.paging.PositionalDataSource
import com.hazymoonstudio.legaldecision.data.RepositoryDataSource
import com.hazymoonstudio.legaldecision.models.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ArticlesDataSource(private val repository: RepositoryDataSource, private val scope: CoroutineScope) : PositionalDataSource<Article>() {
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
            val articles = repository.getArticleListFromPositionWithLimit(mTotalCount, loadSize)
            //Log.i("ArticlesDataSource", "StartPos: $mTotalCount, LoadSize: $loadSize")
            mTotalCount += loadSize
            callback(articles)
        }
    }
}