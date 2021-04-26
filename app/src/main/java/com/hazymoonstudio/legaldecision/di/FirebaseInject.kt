package com.hazymoonstudio.legaldecision.di

import androidx.core.text.HtmlCompat
import androidx.paging.PagedList
import com.firebase.ui.firestore.SnapshotParser
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import com.hazymoonstudio.legaldecision.data.CloudFireStoreDataSource
import com.hazymoonstudio.legaldecision.data.RepositoryDataSource
import com.hazymoonstudio.legaldecision.models.Article
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FirebaseInject {

    @Provides
    fun providesFirebaseFireStore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun providesCloudFireStoreDataSourceRepository(fireStore: FirebaseFirestore): RepositoryDataSource = CloudFireStoreDataSource(fireStore)

    @Provides
    fun providesFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesFirebaseConfig(): PagedList.Config {
        return PagedList.Config.Builder()
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .setPrefetchDistance(2)
            .setPageSize(10)
            .build()
    }

    @Provides
    fun providesFirebaseOptions(query: Query, config: PagedList.Config): FirestorePagingOptions<Article> {
        return FirestorePagingOptions.Builder<Article>()
            .setQuery(query, config
            ) { snapshot ->
                var article = Article()

                article.articleId = snapshot.id
                snapshot.toObject(Article::class.java)?.let {
                    article = it
                }
//               article.title = snapshot.getString("title").toString()
//               article.text = HtmlCompat.fromHtml(snapshot.getString("text").toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)

                article
            }
            .build()
    }

    @Provides
    fun provideFirebaseQuery(database: FirebaseFirestore): Query = database.collection(CloudFireStoreDataSource.UKRAINIAN_LAWS_TABLE)
}