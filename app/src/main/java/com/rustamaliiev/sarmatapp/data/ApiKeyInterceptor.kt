package com.rustamaliiev.sarmatapp.data

import com.rustamaliiev.sarmatapp.data.SystemConfig.API_KEY
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val url = original.url.newBuilder().addQueryParameter("api_key", API_KEY).build()

        val request = original.newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}