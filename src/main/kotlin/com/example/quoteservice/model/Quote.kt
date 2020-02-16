package com.example.quoteservice.model

import com.example.quoteservice.validation.QuoteValid
import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.Size

@Entity
@QuoteValid
data class Quote(
        @Id
        @field:Size(min = 12, max = 12, message = "isin must be 12 symbols length")
        val isin: String,
        val bid: BigDecimal?,
        val ask: BigDecimal,
        var elvl: BigDecimal?
): Serializable