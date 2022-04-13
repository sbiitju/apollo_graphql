package com.shahinbasahr.apollo.di

import com.shahinbasahr.apollo.network.CharacterRepositoryIMP
import com.shahinbasahr.apollo.network.ICharacterRepositoryIMP
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
    abstract fun bindRepository(characterRepositoryIMP: CharacterRepositoryIMP):ICharacterRepositoryIMP
}