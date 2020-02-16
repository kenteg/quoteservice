package com.example.quoteservice.validation

import com.example.quoteservice.model.Quote
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class QuoteValidator: ConstraintValidator<QuoteValid, Quote> {
    override fun isValid(value: Quote?, context: ConstraintValidatorContext?): Boolean {
        return (value?.bid==null || (value.bid > value.ask))
    }

}
