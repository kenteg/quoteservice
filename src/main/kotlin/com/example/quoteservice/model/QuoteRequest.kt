package com.example.quoteservice.model

import java.io.Serializable
import javax.validation.Valid

data class QuoteRequest (
        @field:Valid
        val quotes: MutableList<Quote>
): Serializable