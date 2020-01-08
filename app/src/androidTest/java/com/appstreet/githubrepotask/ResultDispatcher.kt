package com.gojektask.githubrepo

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.appstreet.githubrepotask.MockConstants.LIST_JSON
import com.appstreet.githubrepotask.Util.asset
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class ResultDispatcher(
private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
) : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        val responseBody = asset(context, LIST_JSON)
        return MockResponse().setResponseCode(200).setBody(responseBody)
    }
}
