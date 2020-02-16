package com.example.quoteservice.repository

import com.example.quoteservice.model.Quote
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.repository.CrudRepository
import javax.persistence.LockModeType

interface QuoteRepository : CrudRepository<Quote, String> {
    fun findOneByIsin(isin: String): Quote?

    @Lock(LockModeType.PESSIMISTIC_READ)
    fun findAllByIsinIn(isin: Iterable<String>): List<Quote>

    fun findAllByOrderByIsin(): List<Quote>
}