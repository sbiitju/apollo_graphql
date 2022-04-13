package com.shahinbasahr.apollo.network

import com.apollographql.apollo.api.Response
import com.shahinbashar.apollo.CharactersListQuery

interface ICharacterRepositoryIMP {
    suspend fun quearyCharacterList(): Response<CharactersListQuery.Data>
}