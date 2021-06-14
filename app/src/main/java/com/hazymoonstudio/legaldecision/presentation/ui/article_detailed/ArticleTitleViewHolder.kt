package com.hazymoonstudio.legaldecision.presentation.ui.article_detailed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.hazymoonstudio.legaldecision.R
import com.hazymoonstudio.legaldecision.databinding.TextItemBinding
import com.hazymoonstudio.legaldecision.databinding.TitleItemBinding
import com.hazymoonstudio.legaldecision.domain.model.Component

class ArticleTitleViewHolder(private val itemBinding: TitleItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
    fun bind(component: Component){
        itemBinding.component = component
        itemBinding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): ArticleTitleViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val itemBinding = TitleItemBinding.inflate(layoutInflater, parent, false)
            return ArticleTitleViewHolder(itemBinding = itemBinding)
        }
    }
}