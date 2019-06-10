package com.iambedant.mvistarter.feature.user

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

class UserViewModelFactory @Inject constructor(private val userActionProcessorHolder: UserActionProcessorHolder)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(userActionProcessorHolder) as T
    }
}