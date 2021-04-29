package com.hazymoonstudio.legaldecision.presentation.ui.articles_list

import androidx.paging.DataSource
import com.hazymoonstudio.legaldecision.domain.model.Article
import com.hazymoonstudio.legaldecision.repository.ArticleRepository
import kotlinx.coroutines.CoroutineScope

class ArticlesDataSourceFactory(
    private val repository: ArticleRepository,
    private val scope: CoroutineScope
    ): DataSource.Factory<Int, Article>(){

    override fun create(): DataSource<Int, Article> {
        return ArticlesDataSource(repository, scope)
    }
}