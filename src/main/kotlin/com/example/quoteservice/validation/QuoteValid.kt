package com.example.quoteservice.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [QuoteValidator::class])
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class QuoteValid (val message: String = "bid must be > ask", val groups: Array<KClass<*>> = [], val payload: Array<KClass<out Payload>> = [])