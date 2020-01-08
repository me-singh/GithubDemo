package com.appstreet.githubrepotask

class TestApp : App() {
    override val baseUrl: String
        get() = "http://127.0.0.1:${BuildConfig.PORT}"
}