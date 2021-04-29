package com.hazymoonstudio.legaldecision.presentation.ui.articles_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.hazymoonstudio.legaldecision.databinding.ItemArticleListBinding
import com.hazymoonstudio.legaldecision.domain.model.Article

class ArticlesListViewHolder(private val itemBinding: ItemArticleListBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(article: Article) {
        itemBinding.article = article
        itemBinding.executePendingBindings()
        itemBinding.click = object : ArticlesClickHandler {
            override fun onArticleClick(view: View) {
                itemBinding.root.findNavController().navigate(ArticlesListFragmentDirections.actionArticlesListFragmentToArticleDetailedFragment(article.articleId))
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): ArticlesListViewHolder {
            var layoutInflater = LayoutInflater.from(parent.context)
            var itemBinding = ItemArticleListBinding.inflate(layoutInflater, parent, false)
            return ArticlesListViewHolder(itemBinding)
        }
    }
}