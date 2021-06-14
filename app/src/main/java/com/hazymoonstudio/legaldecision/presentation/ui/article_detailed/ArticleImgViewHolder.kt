package com.hazymoonstudio.legaldecision.presentation.ui.article_detailed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.hazymoonstudio.legaldecision.R
import com.hazymoonstudio.legaldecision.domain.model.Component

class ArticleImgViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
    fun bind(component: Component){

    }

    companion object {
        fun create(parent: ViewGroup): ArticleImgViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.text_item, parent, false)
            return ArticleImgViewHolder(view)
        }
    }
}