package com.example.quoteservice.controller

import com.example.quoteservice.QuoteserviceApplication
import com.example.quoteservice.model.Quote
import com.example.quoteservice.repository.QuoteRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal
import java.nio.charset.Charset

@SpringBootTest
@AutoConfigureMockMvc
class QuoteControllerTest{

    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var quoteRepository: QuoteRepository


    @Test
    fun getAllElvl() {
        quoteRepository.save(Quote("aaaaaaaaaaaa", BigDecimal.TEN, BigDecimal.ONE, BigDecimal.TEN))

        mvc.perform(MockMvcRequestBuilders
                .get("/allelvls")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("\$.[:1].isin").exists())
        quoteRepository.deleteAll()
    }

    @Test
    fun saveQuotes() {
        val request = this.javaClass.getResource("/update.json")?.readText(Charset.forName("UTF-8"))!!

        mvc.perform(MockMvcRequestBuilders
                .post("/updatequotes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)


        assertEquals(9, quoteRepository.findAllByOrderByIsin().size)
        quoteRepository.deleteAll()
    }


    @Test
    fun getByIsin() {
        val isin = "aaaaaaaaaaaa"
        quoteRepository.save(Quote(isin, BigDecimal.TEN, BigDecimal.ONE, BigDecimal.TEN))

        mvc.perform(MockMvcRequestBuilders
                .get("/elvl/$isin")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("\$.isin").value(isin))
                .andExpect(MockMvcResultMatchers.jsonPath("\$.elvl").value("10.0"))
        quoteRepository.deleteAll()
    }
}