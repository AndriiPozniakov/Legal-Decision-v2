package com.hazymoonstudio.legaldecision.models

import android.text.Spanned
import androidx.core.text.HtmlCompat

data class Article(var articleId: String = "", var title: String = "", var text: Spanned = HtmlCompat.fromHtml("", HtmlCompat.FROM_HTML_MODE_LEGACY))