package com.shahinbasahr.apollo.di

import com.aisavent.data.location.LocationDataSource
import com.aisavent.data.location.LocationGraphQLApi
import com.aisavent.data.location.LocationNetworkSource
import com.aisavent.data.location.LocationRepository
import com.shahinbasahr.apollo.network.BaseRepo
import com.shahinbasahr.apollo.network.BaseRepoIMP
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    @ViewModelScoped
    abstract fun bindBaseRepository(baseRepoIMP: BaseRepoIMP):BaseRepo


    @Binds
    @ViewModelScoped
    abstract fun bindLocationRepository(locationRepository: LocationRepository):LocationDataSource

//    @Binds
//    @ViewModelScoped
//    abstract fun provideViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

}