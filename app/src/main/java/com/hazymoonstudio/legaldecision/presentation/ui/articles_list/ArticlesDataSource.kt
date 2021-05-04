package com.hazymoonstudio.legaldecision.presentation.ui.articles_list

import androidx.paging.ItemKeyedDataSource
import com.hazymoonstudio.legaldecision.domain.model.Article
import com.hazymoonstudio.legaldecision.repository.ArticleRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.hazymoonstudio.legaldecision.presentation.ui.articles_list.ArticlesDataSource.RequestType.*

class ArticlesDataSource(private val repository: ArticleRepository, private val scope: CoroutineScope) : ItemKeyedDataSource<String, Article>() {
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<Article>) {
        executeQuery(loadSize = params.requestedLoadSize, type = FIRST_REQUEST) {
            callback.onResult(it)
        }
    }

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<Article>) {
        executeQuery(loadSize = params.requestedLoadSize, type = NEXT_REQUEST, lastArticleId = params.key) {
            callback.onResult(it)
        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<Article>) {
        executeQuery(loadSize = params.requestedLoadSize, type = BEFORE_REQUEST, lastArticleId = params.key) {
            callback.onResult(it)
        }
    }

    override fun getKey(item: Article): String {
        return item.articleId
    }

    private fun executeQuery(loadSize: Int, type: RequestType, lastArticleId: String = "", callback: (List<Article>) -> Unit) {
        scope.launch {
            val articles = ArrayList<Article>()

            when(type) {
                FIRST_REQUEST -> {
                    articles.addAll(repository.getFirstArticles(loadSize = loadSize))
                }
                NEXT_REQUEST -> {
                    articles.addAll(repository.getNextArticles(loadSize = loadSize, lastArticleId = lastArticleId))
                }
                BEFORE_REQUEST -> {

                }
            }

            callback(articles)
        }
    }

    enum class RequestType {
        FIRST_REQUEST, NEXT_REQUEST, BEFORE_REQUEST
    }
}