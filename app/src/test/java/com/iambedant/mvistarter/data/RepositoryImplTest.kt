package com.iambedant.mvistarter.data

import com.iambedant.mvistarter.data.remote.Network
import com.iambedant.mvistarter.data.remote.model.*
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RepositoryImplTest {
    @Mock
    private lateinit var network: Network
    private lateinit var repository: RepositoryImpl
    @Before
    fun setUpRepository(){
        MockitoAnnotations.initMocks(this)
        repository = RepositoryImpl(network)
    }

    @Test
    fun loadNewsTest(){
        whenever(network.loadNews()).thenReturn(Single.just(newsResponse))
        repository.loadNews()
        Mockito.verify<Network>(network).loadNews()
    }

    @Test
    fun doLogin(){
        whenever(network.doLogin(loginRequest)).thenReturn(Single.just(loginResponse))
        repository.doLogin(loginRequest)
        Mockito.verify<Network>(network).doLogin(loginRequest)
    }

    @Test
    fun loadPosts()
    {
        val articles = listOf<Post>()
        whenever(network.loadPosts()).thenReturn(Observable.just(articles))
        repository.loadPosts()
        Mockito.verify<Network>(network).loadPosts()
    }

    @Test
    fun loadComments()
    {
        val comments = listOf<Comments>()
        whenever(network.loadComments()).thenReturn(Observable.just(comments))
        repository.loadComment()
        Mockito.verify<Network>(network).loadComments()
    }

    companion object {
        private val newsResponse = NewsResponse(0, listOf(),"")
        private val loginResponse = LoginResponse("", Response("",""))
        private val loginRequest = LoginRequest("","")

    }
}