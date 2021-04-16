package com.hazymoonstudio.legaldecision.ui.articles_list

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.hazymoonstudio.legaldecision.R
import com.hazymoonstudio.legaldecision.databinding.ItemArticleListBinding
import com.hazymoonstudio.legaldecision.models.Article
import com.hazymoonstudio.legaldecision.utils.MyTextUtils
import kotlin.random.Random

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