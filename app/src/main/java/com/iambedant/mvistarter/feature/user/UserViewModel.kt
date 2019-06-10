package com.iambedant.mvistarter.feature.user

import com.iambedant.mvistarter.feature.base.BaseViewModel
import com.iambedant.mvistarter.mvibase.MviActionProcessorHolder
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction

class UserViewModel(private val userActionProcessorHolder:
                    UserActionProcessorHolder) : BaseViewModel<UserIntent,UserViewState,UserAction,UserResult>()
{
    override fun intentFilter(): ObservableTransformer<UserIntent, UserIntent> {
            return ObservableTransformer { intents ->
                intents.publish { shared ->
                    Observable.merge<UserIntent>(
                            shared.ofType(UserIntent.InitialIntent::class.java).take(1),
                            shared.filter { it != UserIntent.InitialIntent }
                    )
                }
            }


        }

    override fun actionFromIntent(intent: UserIntent): UserAction {
        return when (intent) {
            is UserIntent.InitialIntent -> UserAction.LoadUserAction
            is UserIntent.ClickIntent -> UserAction.ClickAction(intent.comments)
        }
    }

    override fun actionProcessorHolder(): MviActionProcessorHolder<UserAction, UserResult> = userActionProcessorHolder

    override fun reducer(): BiFunction<UserViewState, UserResult, UserViewState> = reducer

    override fun initialState(): UserViewState = UserViewState.idle()

    init {
        connectObservableToLiveData()
    }
    companion object {
        private val reducer = BiFunction { previousState: UserViewState, result: UserResult ->
            when (result) {
                is UserResult.LoadUserResult -> {
                    when (result) {
                        is UserResult.LoadUserResult.Success -> {
                            previousState.copy(isLoading = false, isError = false, errorMessage = "", comments = result.commentList,showShareOption = true)
                        }
                        is UserResult.LoadUserResult.Failure -> {
                            previousState.copy(isLoading = false, isError = true, errorMessage = result.errorMessage)
                        }
                        is UserResult.LoadUserResult.InFlight -> {
                            previousState.copy(isLoading = true, isError = false, errorMessage = "")
                        }
                    }
                }
                is UserResult.ClickResult -> {
                    previousState.copy(showShareOption = true, shareArticle = result.comment)
                }
            }
        }
    }

}