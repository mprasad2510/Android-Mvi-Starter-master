package com.iambedant.mvistarter.data.remote

import com.iambedant.mvistarter.data.remote.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call


interface Network {
    fun doLogin(loginRequest: LoginRequest): Single<LoginResponse>
    fun loadNews(): Single<NewsResponse>
    fun loadPosts(): Observable<List<Post>>
    fun loadComments(): Observable<List<Comments>>
    fun loadUser():Observable<List<User>>
}