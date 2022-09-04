package com.example.breakingbad.data.model

import com.example.breakingbad.domain.model.Quote
import com.google.gson.annotations.SerializedName


class QuoteListDTO : ArrayList<QuoteDTO>()


data class QuoteDTO(
    @SerializedName("quote_id")
    val quoteId: Int,
    val quote: String,
    val author: String,
)

fun QuoteDTO.toQuote(): Quote {
    return Quote(
        quote = this.quote
    )
}
