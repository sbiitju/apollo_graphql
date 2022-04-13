package com.shahinbasahr.apollo.network


class CharacterRepositoryIMP (
    private val webService:ProvideApolloClient
) : ICharacterRepositoryIMP {
//    override suspend fun quearyCharacterList():Response<CharactersListQuery.Data>{
//        return webService.getApolloClient().query(CharactersListQuery()).await()
//    }
}