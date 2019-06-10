package com.iambedant.mvistarter.feature.home

import com.iambedant.mvistarter.data.remote.model.ArticlesItem
import com.iambedant.mvistarter.data.remote.model.Post
import com.iambedant.mvistarter.mvibase.MviViewState



data class HomeViewState(val isLoading: Boolean,
                         val isError: Boolean,
                         val errorMessage: String,
                         val articles: List<Post>,
                         val showShareOption: Boolean,
                         val shareArticle: Post?
) : MviViewState {

    companion object {
        fun idle(): HomeViewState {
            return HomeViewState(
                    isLoading = false,
                    isError = false,
                    errorMessage = "",
                    articles = emptyList(),
                    showShareOption = false,
                    shareArticle = null
            )
        }
    }
}