package com.iambedant.mvistarter.home

import com.iambedant.mvistarter.feature.home.HomeActivity
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import junit.framework.Assert.assertNotNull
import org.robolectric.Robolectric
import org.junit.Before
import org.junit.Test
import com.iambedant.mvistarter.data.remote.model.Post
import com.iambedant.mvistarter.feature.home.NewsAdapter
import org.junit.Assert
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class HomeActivityTest {

    var activity : HomeActivity = null!!
    private  var postList : RecyclerView
    private  var linearLayoutManger : LinearLayoutManager
    private var postLists : List<Post>
    private val share:(Post)->Unit

    @Before
    @Throws(Exception::class)
    fun setUp() {

        activity = Robolectric.buildActivity(HomeActivity::class.java!!)
                .create()
                .resume()
                .get()
        postList.layout(0,0,500,500)

        linearLayoutManger = LinearLayoutManager(RuntimeEnvironment.application, LinearLayoutManager.VERTICAL, false)
        postList.layoutManager = linearLayoutManger
        val adapter = NewsAdapter(postLists,share)
        postList.adapter = adapter

    }


    @Test
    fun itShouldBeLinearLayoutManagerAndVerticalScrollable() {
        Assert.assertEquals(linearLayoutManger, postList.layoutManager)
        Assert.assertEquals(true, postList.layoutManager?.canScrollVertically())
    }
}