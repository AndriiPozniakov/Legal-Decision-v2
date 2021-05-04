package com.hazymoonstudio.legaldecision.presentation.ui.articles_list

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.hazymoonstudio.legaldecision.domain.model.Article
import javax.inject.Inject

class ArticlesListAdapter @Inject constructor(): PagedListAdapter<Article, ArticlesListViewHolder>(ArticleDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesListViewHolder {
        return ArticlesListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ArticlesListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        val ArticleDiffCallback = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.articleId == newItem.articleId
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
//                        && oldItem.components == newItem.components
            }
        }
    }
}