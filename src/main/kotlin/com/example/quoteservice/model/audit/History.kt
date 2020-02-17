package com.example.quoteservice.model.audit

import com.example.quoteservice.validation.QuoteValid
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.Size

@Entity
data class History(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = 0,
        val isin: String,
        val bid: BigDecimal?,
        val ask: BigDecimal,
        val createTime: LocalDateTime,
        val action: String?

): Serializable