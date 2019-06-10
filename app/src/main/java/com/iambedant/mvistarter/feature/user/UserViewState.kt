package com.iambedant.mvistarter.feature.user

import com.iambedant.mvistarter.data.remote.model.Comments
import com.iambedant.mvistarter.mvibase.MviViewState

data class UserViewState (val isLoading: Boolean,
                          val errorMessage: String,
                          val isError: Boolean,
                          val comments: List<Comments>,
                          val showShareOption: Boolean,
                          val shareArticle: Comments?
                          ) : MviViewState
{
    companion object {
        fun idle(): UserViewState {
            return UserViewState(
                    isLoading = false,
                    isError = false,
                    errorMessage = "",
                    comments = emptyList(),
                    showShareOption = false,
                    shareArticle = null
            )
        }
    }
}