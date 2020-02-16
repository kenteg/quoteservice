package com.example.quoteservice.validation

import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError

object ValidationUtils {

    fun buildValidationErrorMessage(bindingResult: BindingResult): String{
        return bindingResult.allErrors.joinToString(separator = "\n") { (it as FieldError).field + ":" + it.defaultMessage }
    }
}