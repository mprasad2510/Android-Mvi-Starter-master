package com.iambedant.mvistarter.feature.home

import com.iambedant.mvistarter.data.remote.model.Post
import com.iambedant.mvistarter.mvibase.MviAction


sealed class HomeAction : MviAction {
    object LoadHomeAction : HomeAction()
    data class ClickAction(val article: Post) : HomeAction()
}