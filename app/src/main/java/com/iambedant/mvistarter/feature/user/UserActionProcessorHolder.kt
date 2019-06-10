package com.iambedant.mvistarter.feature.user

import com.iambedant.mvistarter.data.Repository
import com.iambedant.mvistarter.mvibase.MviActionProcessorHolder
import com.iambedant.mvistarter.util.schedulers.BaseSchedulerProvider
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import javax.inject.Inject

class UserActionProcessorHolder @Inject constructor(private val repository: Repository,
                                                    private val schedulerProvider: BaseSchedulerProvider) : MviActionProcessorHolder<UserAction, UserResult> {
    override fun transformFromAction(): ObservableTransformer<UserAction, UserResult> {
        return ObservableTransformer { action ->
            action.publish { shared ->
                Observable.merge(
                        shared.ofType(UserAction.LoadUserAction::class.java).compose(loadHome()),
                        shared.ofType(UserAction.ClickAction::class.java).compose(shareArticle())

                )
            }
        }
    }


    private fun shareArticle(): ObservableTransformer<UserAction.ClickAction, UserResult.ClickResult> {
        return ObservableTransformer { action ->
            action.flatMap {
                Observable.just(UserResult.ClickResult(it.comments))
            }
        }

    }

    private fun loadHome(): ObservableTransformer<UserAction.LoadUserAction, UserResult.LoadUserResult> {
        return ObservableTransformer { action ->
            action.flatMap {
                repository.loadComment()
                        .map { response -> UserResult.LoadUserResult.Success(response) }
                        .cast(UserResult.LoadUserResult::class.java)
                        .onErrorReturn { t ->
                            UserResult.LoadUserResult.Failure(t.localizedMessage)
                        }
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .startWith(UserResult.LoadUserResult.InFlight)
            }
        }

    }


}