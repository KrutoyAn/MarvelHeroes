package com.example.diffutilsample.di

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

private const val AUTHORIZATION_HEADER = "apikey"
private const val HASH = "hash"

open class AuthorizationInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return proceedRequestWithAuthorization(chain)
    }


    private fun proceedRequestWithAuthorization(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(
                AUTHORIZATION_HEADER,
                "497afb84acc8ba25b849461b510d5cc1"
            )
            .addQueryParameter(
                HASH,
                "d7218865ddcf306a5e181d4023626660"
            )
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}