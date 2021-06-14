package com.hazymoonstudio.legaldecision.network.model

//data class Article(var articleId: String = "", var title: String = "", var text: Spanned = HtmlCompat.fromHtml("", HtmlCompat.FROM_HTML_MODE_LEGACY))
data class ArticleDto(
        var articleId: String = "",
        var title: String = "",
        var text: String = "",
        var imgUrl: String = "",
        var components: List<ComponentDto> = ArrayList()
)