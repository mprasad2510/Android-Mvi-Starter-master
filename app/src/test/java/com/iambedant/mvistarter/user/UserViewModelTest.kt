package com.iambedant.mvistarter.user

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.iambedant.mvistarter.data.Repository
import com.iambedant.mvistarter.data.remote.model.Comments
import com.iambedant.mvistarter.feature.user.UserActionProcessorHolder
import com.iambedant.mvistarter.feature.user.UserIntent
import com.iambedant.mvistarter.feature.user.UserViewModel
import com.iambedant.mvistarter.feature.user.UserViewState
import com.iambedant.mvistarter.util.schedulers.BaseSchedulerProvider
import com.iambedant.mvistarter.util.schedulers.ImmediateSchedulerProvider
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UserViewModelTest {

    @Mock
    private lateinit var repository: Repository
    private lateinit var schedulerProvider: BaseSchedulerProvider
    private lateinit var userViewModel: UserViewModel

    @Mock
    lateinit var observer: Observer<UserViewState>

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setUpHomeViewModel() {
        MockitoAnnotations.initMocks(this)
        schedulerProvider = ImmediateSchedulerProvider()
        userViewModel = UserViewModel((UserActionProcessorHolder(repository, schedulerProvider)))
        userViewModel.states().observeForever(observer)
    }

    @Test
    fun InitialIntentTest() {
        val comments = listOf<Comments>()
        whenever(repository.loadComment()).thenReturn(Observable.just(comments))
        userViewModel.processIntents(Observable.just(UserIntent.InitialIntent))
        verify(observer).onChanged(UserViewState(
                isLoading = true,
                isError = false,
                errorMessage = "",
                comments = emptyList(),
                showShareOption = false,
                shareArticle = null
        ))
        //TODO: figure out why this viewstate in invoked twice
        verify(observer, times(1)).onChanged(UserViewState(
                isLoading = false,
                isError = false,
                errorMessage = "",
                comments = comments,
                shareArticle = null,
                showShareOption = false
        ))
    }

    @Test
    fun LoadErrorTest() {
        whenever(repository.loadComment()).thenReturn(Observable.error(Throwable("This is somekind of error")))
        userViewModel.processIntents(Observable.just(UserIntent.InitialIntent))
        verify(observer).onChanged(UserViewState(
                isLoading = false,
                isError = false,
                errorMessage = "",
                comments = emptyList(),
                showShareOption = false,
                shareArticle = null
        ))
        verify(observer).onChanged(UserViewState(
                isLoading = false,
                isError = true,
                errorMessage = "This is somekind of error",
                comments = emptyList(),
                shareArticle = null,
                showShareOption = false
        ))
    }


    }
