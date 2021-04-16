package com.hazymoonstudio.legaldecision.ui.articles_list

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hazymoonstudio.legaldecision.data.RepositoryDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class ArticlesListViewModel @Inject constructor(private val repository: RepositoryDataSource) : ViewModel() {
    private val ioScope = CoroutineScope(Dispatchers.Default)
    private val userDataSource = ArticlesDataSourceFactory(repository = repository, scope = ioScope)
    val articles = LivePagedListBuilder(userDataSource, pagedListConfig()).build()

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(10)
        .setEnablePlaceholders(false)
        .setPrefetchDistance(2)
        .setPageSize(10)
        .build()

    override fun onCleared() {
        super.onCleared()
        ioScope.coroutineContext.cancel()
    }
}