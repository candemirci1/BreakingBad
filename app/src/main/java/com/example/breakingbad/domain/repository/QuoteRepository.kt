package com.example.breakingbad.domain.repository

import com.example.breakingbad.data.utils.Response
import com.example.breakingbad.domain.model.Quote

interface QuoteRepository {
    suspend fun getQuotes(name:String): Response<List<Quote>>
}