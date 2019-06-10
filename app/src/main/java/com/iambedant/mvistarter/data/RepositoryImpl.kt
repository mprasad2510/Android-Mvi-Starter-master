package com.iambedant.mvistarter.data

import com.iambedant.mvistarter.data.remote.Network
import com.iambedant.mvistarter.data.remote.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Callback
import javax.inject.Inject


class RepositoryImpl @Inject constructor(private val network: Network) : Repository{
    override fun loadComment(): Observable<List<Comments>> {
        return network.loadComments()
    }

    override fun loadUser(): Observable<List<User>> {
        return network.loadUser()
    }

    override fun loadNews(): Single<NewsResponse> {
        return network.loadNews()
    }

    override fun loadPosts(): Observable<List<Post>> {
        return network.loadPosts()
    }

    override fun doLogin(loginRequest: LoginRequest): Single<LoginResponse> {
        return network.doLogin(loginRequest)
    }
}