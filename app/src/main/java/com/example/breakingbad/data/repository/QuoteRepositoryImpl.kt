package com.example.breakingbad.data.repository

import com.example.breakingbad.data.model.toQuote
import com.example.breakingbad.data.service.BreakingBadService
import com.example.breakingbad.data.utils.Response
import com.example.breakingbad.domain.model.Quote
import com.example.breakingbad.domain.repository.QuoteRepository
import retrofit2.HttpException
import java.io.IOException

class QuoteRepositoryImpl(private val service: BreakingBadService): QuoteRepository {
    override suspend fun getQuotes(name:String): Response<List<Quote>> {
        return try {
            val quotes = service.fetchQuotes(name)
            Response.Success(quotes.map { it.toQuote() })
        } catch (e: HttpException) {
            Response.Error(e.message.orEmpty(), e.code())
        } catch (e: IOException) {
            Response.Error("check your internet connection")
        }
    }
}