package com.hazymoonstudio.legaldecision.presentation.articles_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hazymoonstudio.legaldecision.databinding.ItemArticleListBinding
import com.hazymoonstudio.legaldecision.models.Article

class ArticlesListViewHolder(private val mBinding: ItemArticleListBinding) : RecyclerView.ViewHolder(mBinding.root) {
    fun bind(article: Article) {
        //article.text = MyTextUtils.getSafeSubstringWords(article.text, 20) + "..."
        mBinding.article = article
        mBinding.executePendingBindings()
        mBinding.click = object : ArticlesClickHandler {
            override fun onArticleClick(view: View) {
                mBinding.root.findNavController().navigate(ArticlesListFragmentDirections.actionArticlesListFragmentToArticleDetailedFragment(article.articleId))
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