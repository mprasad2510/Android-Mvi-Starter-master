package com.iambedant.mvistarter.data.remote

import com.iambedant.mvistarter.data.remote.model.*
import com.iambedant.mvistarter.data.remote.retrofit.NetworkApi
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call


class NetworkImpl(private val networkApi: NetworkApi) : Network {
    override fun loadUser(): Observable<List<User>> = networkApi.loadUser()
    override fun doLogin(loginRequest: LoginRequest) = networkApi.doLogin(loginRequest)
    override fun loadNews(): Single<NewsResponse> = networkApi.loadNews()
    override fun loadPosts(): Observable<List<Post>> = networkApi.loadPost()
    override fun loadComments(): Observable<List<Comments>> = networkApi.loadComments()

}