package com.iambedant.mvistarter.feature.user

import com.iambedant.mvistarter.data.remote.model.Comments
import com.iambedant.mvistarter.mvibase.MviResult

sealed class UserResult : MviResult {
    sealed class LoadUserResult : UserResult() {
        data class Success(val commentList: List<Comments>) : LoadUserResult()
        data class Failure(val errorMessage: String) : LoadUserResult()
        object InFlight : LoadUserResult()
    }

    data class ClickResult(val comment: Comments) : UserResult()
}