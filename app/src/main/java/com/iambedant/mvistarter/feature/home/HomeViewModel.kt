package com.iambedant.mvistarter.feature.home

import com.iambedant.mvistarter.feature.base.BaseViewModel
import com.iambedant.mvistarter.mvibase.MviActionProcessorHolder
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction


class HomeViewModel(private val homeActionProcessorHolder:
                    HomeActionProcessorHolder) :
        BaseViewModel<HomeIntent, HomeViewState, HomeAction, HomeResult>() {

    override fun initialState(): HomeViewState = HomeViewState.idle()
    override fun reducer(): BiFunction<HomeViewState, HomeResult, HomeViewState> = reducer
    override fun actionProcessorHolder(): MviActionProcessorHolder<HomeAction, HomeResult> =homeActionProcessorHolder
    override fun intentFilter(): ObservableTransformer<HomeIntent, HomeIntent> {
        return ObservableTransformer { intents ->
            intents.publish { shared ->
                Observable.merge<HomeIntent>(
                        shared.ofType(HomeIntent.InitialIntent::class.java).take(1),
                        shared.filter { it != HomeIntent.InitialIntent }
                )
            }
        }


    }

    init {
        connectObservableToLiveData()
    }

    override fun actionFromIntent(intent: HomeIntent): HomeAction {
        return when (intent) {
            is HomeIntent.InitialIntent -> HomeAction.LoadHomeAction
            is HomeIntent.ClickIntent -> HomeAction.ClickAction(intent.article)
        }
    }

    companion object {
        private val reducer = BiFunction { previousState: HomeViewState, result: HomeResult ->
            when (result) {
                is HomeResult.LoadHomeResult -> {
                    when (result) {
                        is HomeResult.LoadHomeResult.Success -> {
                            previousState.copy(isLoading = false, isError = false, errorMessage = "", articles = result.newsList,showShareOption = true)
                        }
                        is HomeResult.LoadHomeResult.Failure -> {
                            previousState.copy(isLoading = false, isError = true, errorMessage = result.errorMessage)
                        }
                        is HomeResult.LoadHomeResult.InFlight -> {
                            previousState.copy(isLoading = true, isError = false, errorMessage = "")
                        }
                    }
                }

                is HomeResult.ClickResult -> {
                    previousState.copy(showShareOption = true, shareArticle = result.article)
                }
            }
        }
    }

}