package com.shahinbasahr.apollo.network

import android.content.Context
import android.os.Looper
import com.apollographql.apollo.ApolloClient
import com.shahinbashar.apollo.type.CustomType
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProvideApolloClient @Inject constructor() {
    companion object {
        private lateinit var okHttpClient: OkHttpClient

        fun getApolloClient(context: Context): ApolloClient {
            check(Looper.myLooper() == Looper.getMainLooper()) {
                "Only the main thread can get the apolloClient instance"
            }

            val okHttpClient = OkHttpClient.Builder().build()
            return ApolloClient.builder()
                .serverUrl("https://api-dev.hungrynaki.com/graphql")
                .okHttpClient(okHttpClient)
                .addCustomTypeAdapters()
                .build()
        }

        fun getOkHttpClient(context: Context, timeOut: Long): OkHttpClient {
            val okHttpBuilder = OkHttpClient.Builder()
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
            okHttpClient = okHttpBuilder.build()
            return okHttpClient
        }

    }


}

fun ApolloClient.Builder.addCustomTypeAdapters(): ApolloClient.Builder {
    return addCustomTypeAdapter(CustomType.POINT, GeoPointScalarAdapter())

}