package com.iambedant.mvistarter.data

import com.iambedant.mvistarter.data.remote.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Callback

interface Repository {
    fun doLogin(loginRequest: LoginRequest): Single<LoginResponse>
    fun loadNews(): Single<NewsResponse>
    fun loadPosts(): Observable<List<Post>>
    fun loadComment(): Observable<List<Comments>>
    fun loadUser(): Observable<List<User>>

}