package com.hazymoonstudio.legaldecision.presentation.ui.article_detailed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.hazymoonstudio.legaldecision.R
import com.hazymoonstudio.legaldecision.databinding.TextItemBinding
import com.hazymoonstudio.legaldecision.databinding.TitleAndSubtitleItemBinding
import com.hazymoonstudio.legaldecision.databinding.TitleItemBinding
import com.hazymoonstudio.legaldecision.domain.model.Component

class ArticleTitleAndSubtitleViewHolder(private val itemBinding: TitleAndSubtitleItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
    fun bind(component: Component){
        itemBinding.component = component
        itemBinding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): ArticleTitleAndSubtitleViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val itemBinding = TitleAndSubtitleItemBinding.inflate(layoutInflater, parent, false)
            return ArticleTitleAndSubtitleViewHolder(itemBinding = itemBinding)
        }
    }
}