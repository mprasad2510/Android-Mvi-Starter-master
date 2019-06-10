package com.iambedant.mvistarter.feature.login

import com.iambedant.mvistarter.mvibase.MviIntent



sealed class LoginIntent : MviIntent {
    object InitialIntent : LoginIntent()
    data class DoLoginIntent(val userId: String, val password: String) : LoginIntent()
    data class typeUserIdIntent(val userId: String) : LoginIntent()
    data class typePasswordIntent(val password: String) : LoginIntent()

}