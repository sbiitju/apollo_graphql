package com.shahinbasahr.apollo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.shahinbasahr.apollo.network.ICharacterRepositoryIMP
import com.shahinbasahr.apollo.view.state.ViewState
import com.shahinbashar.apollo.CharactersListQuery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CharacterViewmodel @Inject constructor(
    private val repositoryIMP: ICharacterRepositoryIMP
) : ViewModel() {
    private val _charactersList by lazy { MutableLiveData<ViewState<Response<CharactersListQuery.Data>>>() }
    val charactersList: LiveData<ViewState<Response<CharactersListQuery.Data>>>
        get() = _charactersList
    fun queryCharactersList() = viewModelScope.launch {
        _charactersList.postValue(ViewState.Loading())
        try {
            val response = repositoryIMP.quearyCharacterList()
            _charactersList.postValue(ViewState.Success(response))
        } catch (e: ApolloException) {
            Log.d("ApolloException", "Failure", e)
            _charactersList.postValue(ViewState.Error("Error fetching characters"))
        }
    }

}