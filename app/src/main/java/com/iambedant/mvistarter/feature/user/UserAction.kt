package com.iambedant.mvistarter.feature.user

import com.iambedant.mvistarter.data.remote.model.Comments
import com.iambedant.mvistarter.mvibase.MviAction

sealed class UserAction : MviAction
{
    object LoadUserAction : UserAction()
    data class ClickAction(val comments: Comments) : UserAction()

}