package com.example.quoteservice.model

import java.io.Serializable
import java.math.BigDecimal

data class QuoteElvlResponse (
    val isin: String,
    val elvl: BigDecimal?
): Serializable