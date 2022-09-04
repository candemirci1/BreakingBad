package com.example.breakingbad.ui.quote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breakingbad.data.utils.Response
import com.example.breakingbad.domain.model.Quote
import com.example.breakingbad.domain.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteListViewModel @Inject constructor(
    private val quoteRepository: QuoteRepository
) : ViewModel() {
    val state = MutableStateFlow<QuotesViewState>(QuotesViewState.Loading)

     fun getQuotes(name: String){
        viewModelScope.launch {
            quoteRepository.getQuotes(name).let {
                when(it) {
                    is Response.Loading -> state.value = QuotesViewState.Loading
                    is Response.Success -> state.value = QuotesViewState.Success(it.data)
                    is Response.Error -> state.value = QuotesViewState.Error(it.message.orEmpty())
                }
            }
        }

    }

}



sealed class QuotesViewState {
    object Loading : QuotesViewState()

    data class Success(
        val data: List<Quote>?
    ): QuotesViewState()

    data class Error(
        val message: String
    ) : QuotesViewState()

}