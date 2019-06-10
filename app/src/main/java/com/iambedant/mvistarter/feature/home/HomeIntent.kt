package com.iambedant.mvistarter.feature.home

import com.iambedant.mvistarter.data.remote.model.ArticlesItem
import com.iambedant.mvistarter.data.remote.model.NewsResponse
import com.iambedant.mvistarter.data.remote.model.Post
import com.iambedant.mvistarter.mvibase.MviIntent



sealed class HomeIntent : MviIntent {
    object InitialIntent : HomeIntent()
    data class ClickIntent(val article: Post):HomeIntent()

}