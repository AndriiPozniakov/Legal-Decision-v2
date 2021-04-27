package com.hazymoonstudio.legaldecision.presentation.articles_list

import android.util.Log
import android.view.ViewGroup
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.firebase.ui.firestore.paging.LoadingState
import com.hazymoonstudio.legaldecision.models.Article
import javax.inject.Inject

class ArticlesListAdapter @Inject constructor(options: FirestorePagingOptions<Article>): FirestorePagingAdapter<Article, ArticlesListViewHolder>(options) {
    private val TAG = "Legal Decision"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesListViewHolder {
        return ArticlesListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ArticlesListViewHolder, position: Int, article: Article) {
        holder.bind(article)
    }

    override fun onLoadingStateChanged(state: LoadingState) {
        when (state) {
            LoadingState.LOADING_INITIAL -> {
                Log.i(TAG, "Load Initial")
            }

            LoadingState.LOADING_MORE -> {
                Log.i(TAG, "LOADING_MORE")
            }

            LoadingState.LOADED -> {
                Log.i(TAG, "LOADED")
            }

            LoadingState.ERROR -> {
                Log.i(TAG, "ERROR")
            }

            LoadingState.FINISHED -> {
                Log.i(TAG, "FINISHED")
            }
        }
    }
}