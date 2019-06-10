package com.iambedant.mvistarter.feature.user

import com.iambedant.mvistarter.data.remote.model.Comments
import com.iambedant.mvistarter.mvibase.MviIntent

sealed class UserIntent : MviIntent {
    object InitialIntent : UserIntent()
    data class ClickIntent(val comments: Comments): UserIntent()

}