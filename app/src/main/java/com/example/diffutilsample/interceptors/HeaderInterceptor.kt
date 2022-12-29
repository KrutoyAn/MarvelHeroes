package com.example.diffutilsample.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private const val HEROES_ACCESS_TOKEN = ""

class HeaderInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("access-token", HEROES_ACCESS_TOKEN)
                .build()
        )
    }
}