package com.shahinbasahr.apollo.network

import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.shahinbashar.apollo.CharactersListQuery
import javax.inject.Inject

class CharacterRepositoryIMP @Inject constructor(
    private val webService:ProvideApolloClient
) : ICharacterRepositoryIMP {
    override suspend fun quearyCharacterList():Response<CharactersListQuery.Data>{
        return webService.getApolloClient().query(CharactersListQuery()).await()
    }
}