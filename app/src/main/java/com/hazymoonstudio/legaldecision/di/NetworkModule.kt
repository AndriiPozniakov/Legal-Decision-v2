package com.hazymoonstudio.legaldecision.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.hazymoonstudio.legaldecision.network.ArticleService
import com.hazymoonstudio.legaldecision.network.model.ArticleDtoMapper
import com.hazymoonstudio.legaldecision.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRecipeMapper(): ArticleDtoMapper {
        return ArticleDtoMapper()
    }

//    @Provides
//    fun providesFirebaseConfig(): PagedList.Config {
//        return PagedList.Config.Builder()
//            .setInitialLoadSizeHint(10)
//            .setEnablePlaceholders(false)
//            .setPrefetchDistance(2)
//            .setPageSize(10)
//            .build()
//    }

//    @Provides
//    fun providesFirebaseOptions(query: Query, config: PagedList.Config): FirestorePagingOptions<Article> {
//        return FirestorePagingOptions.Builder<Article>()
//            .setQuery(query, config
//            ) { snapshot ->
//                var article = Article()
//
//                article.articleId = snapshot.id
//                snapshot.toObject(Article::class.java)?.let {
//                    article = it
//                }
////               article.title = snapshot.getString("title").toString()
////               article.text = HtmlCompat.fromHtml(snapshot.getString("text").toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
//
//                article
//            }
//            .build()
//    }
//
//    @Provides
//    fun provideFirebaseQuery(database: FirebaseFirestore): Query = database.collection(CloudFireStoreDataSource.UKRAINIAN_LAWS_TABLE)
}