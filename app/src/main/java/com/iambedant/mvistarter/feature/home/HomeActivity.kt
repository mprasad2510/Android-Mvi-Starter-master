package com.iambedant.mvistarter.feature.home

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import com.iambedant.mvistarter.R
import com.iambedant.mvistarter.mvibase.MviView
import com.iambedant.mvistarter.util.gone
import com.iambedant.mvistarter.util.visible
import dagger.android.AndroidInjector
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.iambedant.mvistarter.data.remote.model.Post
import com.iambedant.mvistarter.feature.base.BaseActivity
import com.iambedant.mvistarter.feature.user.UserActivity
import dagger.android.HasActivityInjector


open class HomeActivity : BaseActivity(), MviView<HomeIntent, HomeViewState>, HasActivityInjector {


    override fun layoutId(): Int = R.layout.activity_home

    @Inject
    lateinit var factory: HomeViewmodelFactory

    override fun bind() {
        newsRv.layoutManager = LinearLayoutManager(this)
        viewModel.processIntents(intents())
        viewModel.states().observe(this, Observer { if (it != null) render(it) })
    }

    private val clickIntent = PublishSubject.create<HomeIntent.ClickIntent>()

    private val viewModel: HomeViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
    }

    private fun initialIntent(): Observable<HomeIntent.InitialIntent> {
        return Observable.just(HomeIntent.InitialIntent)
    }

    override fun intents(): Observable<HomeIntent> {
        return Observable.merge(initialIntent(), clickIntent)
    }

    override fun render(state: HomeViewState) {
        Log.d("***State***","   render $state")
        with(state) {
            if (isLoading) {
                progressBar.visible()
            } else {
                progressBar.gone()
            }
            if (!articles.isEmpty()) {
                Log.d("***State***","   inside $state")
                newsRv.adapter = NewsAdapter(articles, { clickItem -> clickIntent.onNext(HomeIntent.ClickIntent(clickItem)) })
            }

            if(showShareOption){
                showShareIntent(shareArticle,articles)
            }

        }
    }
    private fun showShareIntent(shareArticle: Post?,articles:List<Post>) {
        shareArticle?.let {
            for (position in articles.indices) {
                Log.d("******POS OF ARTICLE", "$position")

                val intent = Intent(this@HomeActivity, UserActivity::class.java)
                intent.putExtra("title", shareArticle.title)
                intent.putExtra("position", position.toString())
                startActivity(intent)
            }
        }
    }


    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }
}
