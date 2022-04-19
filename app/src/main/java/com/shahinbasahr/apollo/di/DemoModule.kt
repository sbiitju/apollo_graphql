package com.shahinbasahr.apollo.di

import com.aisavent.data.location.LocationDataSource
import com.aisavent.data.location.LocationGraphQLApi
import com.aisavent.data.location.LocationNetworkSource
import com.aisavent.data.location.LocationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DemoModule {
    @Binds
    abstract fun bindLocationRepository2(locationGraphQLApi: LocationGraphQLApi): LocationNetworkSource
}