package com.hazymoonstudio.legaldecision.presentation.ui.article_detailed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.hazymoonstudio.legaldecision.R
import com.hazymoonstudio.legaldecision.databinding.ItemArticleListBinding
import com.hazymoonstudio.legaldecision.databinding.TextItemBinding
import com.hazymoonstudio.legaldecision.domain.model.Component
import com.hazymoonstudio.legaldecision.presentation.ui.articles_list.ArticlesListViewHolder

class ArticleTextViewHolder(private val itemBinding: TextItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
    fun bind(component: Component){
        itemBinding.component = component
        itemBinding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): ArticleTextViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val itemBinding = TextItemBinding.inflate(layoutInflater, parent, false)
            return ArticleTextViewHolder(itemBinding = itemBinding)
        }
    }
}