package com.shahinbasahr.apollo.di

import android.os.Looper
import com.apollographql.apollo.ApolloClient
import com.shahinbasahr.apollo.network.ProvideApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApolloClient()=ProvideApolloClient()
}