package com.shahinbasahr.apollo.network

import android.content.Context
import android.os.Looper
import android.preference.PreferenceManager
import com.apollographql.apollo.ApolloClient
import okhttp3.*
import okhttp3.internal.platform.Platform
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class ProvideApolloClient {
    fun getApolloClient(): ApolloClient {
        check(Looper.myLooper() == Looper.getMainLooper()) {
            "Only the main thread can get the apolloClient instance"
        }


        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).build()
        return ApolloClient.builder()
            .serverUrl("https://api-dev.hungrynaki.com/graphql")
            .okHttpClient(okHttpClient)
            .build()
    }

}