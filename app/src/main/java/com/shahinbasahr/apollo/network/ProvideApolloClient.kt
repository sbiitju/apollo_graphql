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


        val okHttpClient = OkHttpClient.Builder().
            .connectionSpecs(Collections.singletonList())
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).build()
        return ApolloClient.builder()
            .serverUrl("https://api-dev.hungrynaki.com/graphql")
            .okHttpClient(okHttpClient)
            .build()
    }
    fun getOkHttpClient(context: Context, timeOut: Long = TIME_OUT): OkHttpClient {
        if (!ApiFactory::okHttpClient.isInitialized) {
            val spec = ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
                .supportsTlsExtensions(true)
                .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0)
                .cipherSuites(
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,
                    CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_DHE_DSS_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA
                )
                .build()

            val okHttpBuilder = OkHttpClient.Builder()
                .connectionSpecs(Collections.singletonList(spec))
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val token = PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_TOKEN, null)
                    if (token?.isNotEmpty() == true) {
                        return@addInterceptor chain.proceed(
                            chain.request().newBuilder()
                                .header("authorization", String.format("Bearer %s", token))
                                .header("user-agent", context.userAgent())
                                .build()
                        )
                    } else {
                        return@addInterceptor chain.proceed(
                            chain.request().newBuilder()
                                .header("user-agent", context.userAgent())
                                .build()
                        )
                    }
                }

            okHttpBuilder.cache(Cache(context.cacheDir, cacheSize))

            okHttpBuilder.addInterceptor(
                LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG)
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .tag("OKHTTP")
                    .request("Request")
                    .response("Response")
                    .executor(Executors.newSingleThreadExecutor())
                    .build()
            )

            okHttpClient = okHttpBuilder.build()
        }
        return okHttpClient
    }

}