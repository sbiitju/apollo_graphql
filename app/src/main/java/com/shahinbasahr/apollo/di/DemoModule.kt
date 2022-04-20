package com.shahinbasahr.apollo.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.shahinbasahr.apollo.location.LocationGraphQLApi
import com.shahinbasahr.apollo.location.LocationNetworkSource
import com.shahinbasahr.apollo.preference.CacheData
import com.shahinbasahr.apollo.preference.CacheDataIMP
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DemoModule {
    @Binds
    abstract fun bindLocationRepository2(locationGraphQLApi: LocationGraphQLApi): LocationNetworkSource

    @Binds
    abstract fun bindPreference(cacheDataIMP: CacheDataIMP): CacheData

    companion object{
        @JvmStatic
        @Provides
        @Singleton
        fun provideSharedPreference(@ApplicationContext applicationContext: Context): SharedPreferences {
            return PreferenceManager.getDefaultSharedPreferences(applicationContext)
        }
    }
}