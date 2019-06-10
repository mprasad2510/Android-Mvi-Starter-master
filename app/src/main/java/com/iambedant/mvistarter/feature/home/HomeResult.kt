package com.iambedant.mvistarter.feature.home

import com.iambedant.mvistarter.data.remote.model.ArticlesItem
import com.iambedant.mvistarter.data.remote.model.Post
import com.iambedant.mvistarter.mvibase.MviResult
import java.util.*



sealed class HomeResult : MviResult {
    sealed class LoadHomeResult : HomeResult() {
        data class Success(val newsList: List<Post>) : LoadHomeResult()
        data class Failure(val errorMessage: String) : LoadHomeResult()
        object InFlight : LoadHomeResult()
    }

    data class ClickResult(val article : Post) : HomeResult()
}