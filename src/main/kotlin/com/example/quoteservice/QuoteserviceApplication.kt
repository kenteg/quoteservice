package com.example.quoteservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class QuoteserviceApplication

fun main(args: Array<String>) {
    runApplication<QuoteserviceApplication>(*args)
}
