package com.example.quoteservice.service

import com.example.quoteservice.model.Quote
import com.example.quoteservice.model.QuoteElvlResponse
import com.example.quoteservice.repository.QuoteRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ElvlService(
        val quoteRepo: QuoteRepository
) {

    @Transactional
    fun processQuotes(quotes: MutableList<Quote>): List<Quote>? {
        val isins = quotes.map { it.isin }.toList()
        val existedQuotes = quoteRepo.findAllByIsinIn(isins).associateBy({ it.isin }, { it })

        val resultList = Collections.synchronizedList(mutableListOf<Quote>())
        quotes.parallelStream().forEach {
            it.elvl = existedQuotes[it.isin]?.elvl
            resultList.add(ElvlCalculator.calcElvl(it))
        }
        quoteRepo.saveAll(resultList)
        return resultList
    }


    fun getAllElvls(): List<QuoteElvlResponse>{
        return quoteRepo.findAllByOrderByIsin().map{ QuoteElvlResponse(it.isin, it.elvl)}
    }
}