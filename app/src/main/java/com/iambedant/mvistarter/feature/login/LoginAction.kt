package com.iambedant.mvistarter.feature.login

import com.iambedant.mvistarter.mvibase.MviAction



sealed class LoginAction : MviAction {
    object LoadLoginAction : LoginAction()
    data class DoLoginAction(val userId: String, val password: String): LoginAction()
    data class TypeUserIdAction(val userId: String) : LoginAction()
    data class TypePasswordAction(val password: String) : LoginAction()
}