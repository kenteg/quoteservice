package com.example.quoteservice

import com.example.quoteservice.model.Quote
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import javax.validation.ConstraintViolation
import javax.validation.Validator


@SpringBootTest
class QuoteValidationTest {

    @Autowired
    lateinit var validator: Validator

    @Test
    fun `when isin gt 12 then invalid`() {

        val quote = Quote("1234567890123", BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO)
        val expectedErrors = mapOf(
                "isin" to "isin must be 12 symbols length"
        )

        val errs = validator.validate(quote)

        assertHasErrors(errs, expectedErrors)

    }

    @Test
    fun `when isin lt 12 then invalid`() {

        val quote = Quote("1234567890123", BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO)
        val expectedErrors = mapOf(
                "isin" to "isin must be 12 symbols length"
        )

        val errs = validator.validate(quote)

        assertHasErrors(errs, expectedErrors)

    }

    @Test
    fun `when isin eq 12 then valid`() {

        val quote = Quote("123456789012", BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ZERO)

        val errs = validator.validate(quote)

        assert(errs.isEmpty())

    }

    @Test
    fun `when bid gt then ask then valid`(){
        val quote = Quote("123456789012", BigDecimal.TEN, BigDecimal.ONE, BigDecimal.ZERO)
        val errs = validator.validate(quote)

        assert(errs.isEmpty())
    }

    @Test
    fun `when bid lt then ask then invalid`(){
        val quote = Quote("123456789012", BigDecimal.ONE, BigDecimal.TEN, BigDecimal.ZERO)
        val expectedErrors = mapOf(
                "" to "bid must be > ask"
        )
        val errs = validator.validate(quote)
        print(errs)

        assertHasErrors(errs, expectedErrors)
    }

    @Test
    fun `when bid eq ask then invalid`(){
        val quote = Quote("123456789012", BigDecimal.TEN, BigDecimal.TEN, BigDecimal.ZERO)
        val expectedErrors = mapOf(
                "" to "bid must be > ask"
        )
        val errs = validator.validate(quote)
        print(errs)

        assertHasErrors(errs, expectedErrors)
    }

    private fun assertHasErrors(actualErrors: Set<ConstraintViolation<Quote>>, expectedErrors: Map<String, String>) {

        expectedErrors.forEach { err ->
            val error = actualErrors.find {
                (it.propertyPath.toString() == err.key) && (it.message == err.value)
            }
            assertNotNull(error, "not found error for field ${err.key} with message ${err.value}!")
            assertTrue {
                actualErrors.contains(error)
            }
        }
    }

}