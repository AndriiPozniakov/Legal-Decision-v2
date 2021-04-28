package com.hazymoonstudio.legaldecision.repository

import com.hazymoonstudio.legaldecision.network.ArticleService
import com.hazymoonstudio.legaldecision.network.model.ArticleDtoMapper

class ArticleRepository_Impl (
    private val articleService: ArticleService,
    private val mapper: ArticleDtoMapper) : ArticleRepository{
}