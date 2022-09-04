package com.example.breakingbad.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.data.utils.Response
import com.example.breakingbad.domain.model.Character
import com.example.breakingbad.domain.repository.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    val state = MutableStateFlow<CharactersViewState>(CharactersViewState.Loading)

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            charactersRepository.getCharacters().let {
                when(it) {
                    is Response.Loading -> state.value = CharactersViewState.Loading
                    is Response.Success -> state.value = CharactersViewState.Success(it.data)
                    is Response.Error -> state.value = CharactersViewState.Error(it.message.orEmpty())
                }
            }
        }
    }

}



sealed class CharactersViewState {
    object Loading : CharactersViewState()

    data class Success(
        val data: List<Character>?
    ): CharactersViewState()

    data class Error(
        val message: String
    ) : CharactersViewState()

}