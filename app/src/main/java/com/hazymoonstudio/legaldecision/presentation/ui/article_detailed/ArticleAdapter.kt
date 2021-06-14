package com.hazymoonstudio.legaldecision.presentation.ui.article_detailed

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hazymoonstudio.legaldecision.domain.model.Component
import com.hazymoonstudio.legaldecision.utils.ComponentType
import com.hazymoonstudio.legaldecision.utils.ComponentType.*

class ArticleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var components: MutableList<Component> = ArrayList()

    fun setComponents(items: List<Component>) {
        components.clear()
        components.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(ComponentType.create(viewType)) {
            TEXT -> ArticleTextViewHolder.create(parent)
            TITLE -> ArticleTitleViewHolder.create(parent)
            TITLE_AND_SUBTITLE -> ArticleTitleAndSubtitleViewHolder.create(parent)
            IMG -> ArticleImgViewHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ArticleTextViewHolder -> holder.bind(component = components[position])
            is ArticleTitleViewHolder -> holder.bind(component = components[position])
            is ArticleTitleAndSubtitleViewHolder -> holder.bind(component = components[position])
            is ArticleImgViewHolder -> holder.bind(component = components[position])
        }
    }

    override fun getItemCount(): Int {
        return components.size
    }

    override fun getItemViewType(position: Int): Int {
        return components[position].type.id
    }
}