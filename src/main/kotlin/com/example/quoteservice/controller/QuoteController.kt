package com.example.quoteservice.controller

import com.example.quoteservice.model.QuoteElvlResponse
import com.example.quoteservice.model.QuoteRequest
import com.example.quoteservice.model.audit.History
import com.example.quoteservice.repository.HistoryRepo
import com.example.quoteservice.service.ElvlService
import com.example.quoteservice.validation.ValidationUtils.buildValidationErrorMessage
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.validation.Valid

@RestController
class QuoteController(
        val elvlService: ElvlService,
        val auditRepo: HistoryRepo
) {

    @PostMapping("/updatequotes", consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun receiveQuotes(@Valid @RequestBody quoteReq: QuoteRequest, bindingResult: BindingResult) : ResponseEntity<String>{
        if (bindingResult.hasErrors()){
            return ResponseEntity(buildValidationErrorMessage(bindingResult), HttpStatus.BAD_REQUEST)
        }
        val quotes = quoteReq.quotes
        auditRepo.saveAll(quotes.map{ History(isin = it.isin, bid = it.bid, ask= it.ask, createTime = LocalDateTime.now(), action = "IN")})
        elvlService.processQuotes(quotes)

        return ResponseEntity(HttpStatus.OK)
    }


    @GetMapping("/allelvls")
    fun getAllElvls(): List<QuoteElvlResponse> {
        return elvlService.getAllElvls()
    }

    @GetMapping("/elvl/{isin}")
    fun getElvlByIsin(@PathVariable isin: String): QuoteElvlResponse{
        return elvlService.getElvlByIsin(isin)
    }
}