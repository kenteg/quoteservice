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
data class Audit(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,
        val isin: String,
        val bid: BigDecimal?,
        val ask: BigDecimal,
        var elvl: BigDecimal?,
        val createDate: LocalDateTime,
        val action: String?

): Serializable