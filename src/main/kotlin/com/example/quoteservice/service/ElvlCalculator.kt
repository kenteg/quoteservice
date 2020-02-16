package com.example.quoteservice.service

import com.example.quoteservice.model.Quote
import java.math.BigDecimal
import java.time.LocalDateTime

object ElvlCalculator {

    fun calcElvl(quote: Quote): Quote {
        return Quote(
                quote.isin,
                quote.bid,
                quote.ask,
                mapElvl(quote)
        )
    }

    fun mapElvl(quote: Quote): BigDecimal?{
        with(quote){
            if (bid == null) return@mapElvl ask
            if ((elvl == null) || (bid > elvl)) return@mapElvl bid
            if (ask < elvl) return@mapElvl ask

        }
        return null
    }
}