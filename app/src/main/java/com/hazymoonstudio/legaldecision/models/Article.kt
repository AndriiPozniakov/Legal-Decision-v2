package com.hazymoonstudio.legaldecision.models

import android.text.Spanned
import androidx.core.text.HtmlCompat

//data class Article(var articleId: String = "", var title: String = "", var text: Spanned = HtmlCompat.fromHtml("", HtmlCompat.FROM_HTML_MODE_LEGACY))
data class Article(var articleId: String = "", var components: MutableList<Component> = ArrayList())
data class Component(var type: Int = 0, var text: String = "", var date: String = "", var time: String = "")