package com.hazymoonstudio.legaldecision.ui.articles_list

import androidx.paging.DataSource
import com.hazymoonstudio.legaldecision.data.RepositoryDataSource
import com.hazymoonstudio.legaldecision.models.Article
import kotlinx.coroutines.CoroutineScope

class ArticlesDataSourceFactory(
    private val repository: RepositoryDataSource,
    private val scope: CoroutineScope
    ): DataSource.Factory<Int, Article>(){

    override fun create(): DataSource<Int, Article> {
        return ArticlesDataSource(repository, scope)
    }
}