package com.hazymoonstudio.legaldecision.presentation.ui.articles_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hazymoonstudio.legaldecision.databinding.ItemArticleListBinding
import com.hazymoonstudio.legaldecision.domain.model.Article
import com.hazymoonstudio.legaldecision.presentation.MainApplication

class ArticlesListViewHolder(private val itemBinding: ItemArticleListBinding) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(article: Article) {
        itemBinding.article = article
        Glide
                .with(itemView)
                .load(article.imgUrl)
                .centerCrop()
                .into(itemBinding.cardImage)
        itemBinding.executePendingBindings()
        itemBinding.click = object : ArticlesClickHandler {
            override fun onArticleClick(view: View) {
                itemBinding.root.findNavController().navigate(ArticlesListFragmentDirections.actionArticlesListFragmentToArticleDetailedFragment(article.articleId))
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): ArticlesListViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemArticleListBinding.inflate(layoutInflater, parent, false)
            return ArticlesListViewHolder(itemBinding)
        }
    }
}