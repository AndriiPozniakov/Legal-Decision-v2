package com.hazymoonstudio.legaldecision.domain.model

import com.hazymoonstudio.legaldecision.domain.model.Component

data class Article(var articleId: String = "", var title: String = "", var components: List<Component> = ArrayList())
