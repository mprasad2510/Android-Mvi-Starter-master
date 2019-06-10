package com.iambedant.mvistarter.feature.user

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.util.Log
import com.iambedant.mvistarter.data.remote.model.Comments
import com.iambedant.mvistarter.feature.base.BaseActivity
import com.iambedant.mvistarter.mvibase.MviView
import com.iambedant.mvistarter.util.gone
import com.iambedant.mvistarter.util.visible
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_user.*
import javax.inject.Inject



open class UserActivity : BaseActivity(), MviView<UserIntent, UserViewState>, HasActivityInjector {

    override fun bind() {
        viewModel.processIntents(intents())
        viewModel.states().observe(this, Observer { if (it != null) render(it) })
    }

    override fun layoutId(): Int = com.iambedant.mvistarter.R.layout.activity_user


    @Inject
    lateinit var factory: UserViewModelFactory

    private val clickIntent = PublishSubject.create<UserIntent.ClickIntent>()

    private val viewModel: UserViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, factory).get(UserViewModel::class.java)
    }

    private fun initialIntent(): Observable<UserIntent.InitialIntent> {
        return Observable.just(UserIntent.InitialIntent)
    }

    override fun intents(): Observable<UserIntent> {
        return Observable.merge(initialIntent(), clickIntent)
    }

    override fun render(state: UserViewState) {
        var title: String = intent.getStringExtra("title")
        var position: Int = intent.getStringExtra("position").toInt()

        with(state) {
            if (isLoading) {
                progressBar1.visible()
            } else {
                progressBar1.gone()
            }
            if (!comments.isEmpty()) {
                with(comments[position]) {
                    text_title.text = title
                    text_desc.text = body1
                    author_name.text = StringBuilder().append("By : ").append(name)
                    noOfComments.text =  StringBuilder().append("Comments : ").append(postId.toString())
                }
            }
            if(showShareOption){
                showShareIntent(shareArticle)
            }

        }
    }

    private fun showShareIntent(shareArticle: Comments?) {
        shareArticle?.let {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    shareArticle.body1)
            sendIntent.type = "text/plain"
            startActivity(sendIntent)
        }
    }


    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }
}