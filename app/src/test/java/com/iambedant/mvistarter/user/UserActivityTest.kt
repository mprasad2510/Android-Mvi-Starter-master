package com.iambedant.mvistarter.user

import android.app.Activity
import android.widget.TextView
import com.iambedant.mvistarter.R
import com.iambedant.mvistarter.feature.user.UserActivity
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.hamcrest.CoreMatchers.equalTo
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(manifest=Config.NONE)
class UserActivityTest {
 private  var activity : UserActivity = null!!

    @Before
    @Throws(Exception::class)
    fun init() {
       activity = Robolectric.buildActivity(UserActivity::class.java)
                .create()
                .get()
    }
    @Test
    fun itShouldProperlySetTheText(){

        val textTitle=activity.findViewById<TextView>(R.id.text_title)
        val stringTitle=textTitle.text.toString()
        assertThat(stringTitle,equalTo("Title"))

        val textDesc=activity.findViewById<TextView>(R.id.text_desc)
        val stringDesc=textDesc.text.toString()
        assertThat(stringDesc,equalTo("Description"))

        val textAuthorName=activity.findViewById<TextView>(R.id.author_name)
        val Author=textAuthorName.text.toString()
        assertThat(Author,equalTo("Author Name"))

        val textComments=activity.findViewById<TextView>(R.id.noOfComments)
        val stringComments=textComments.text.toString()
        assertThat(stringComments,equalTo("No of Comments"))
    }

}