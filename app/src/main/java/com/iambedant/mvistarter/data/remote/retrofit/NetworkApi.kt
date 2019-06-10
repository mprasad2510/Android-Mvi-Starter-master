package com.iambedant.mvistarter.data.remote.retrofit

import com.iambedant.mvistarter.data.remote.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetworkApi {
    companion object {
        const val BASE_URL = "http://demo1570114.mockable.io/"
    }

    @POST("auth/login/")
    fun doLogin (@Body request: LoginRequest): Single<LoginResponse>

    @GET("https://newsapi.org/v2/top-headlines?country=us&apiKey=d33e891e97b74a838f165a171a07abda")
    fun loadNews(): Single<NewsResponse>

    @GET(" http://jsonplaceholder.typicode.com/posts")
    fun loadPost(): Observable<List<Post>>

    @GET(" http://jsonplaceholder.typicode.com/comments")
    fun loadComments(): Observable<List<Comments>>

    @GET("http://jsonplaceholder.typicode.com/users")
    fun loadUser(): Observable<List<User>>

}

