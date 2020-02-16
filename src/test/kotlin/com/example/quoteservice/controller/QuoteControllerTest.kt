package com.example.quoteservice.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootTest
@EnableWebMvc
internal class QuoteControllerTest{

    @Autowired
    lateinit var mvc: MockMvc

    @Test
    fun getAllElvl() {
        mvc.perform(MockMvcRequestBuilders
                .get("/allelvls")
                .accept(MediaType.APPLICATION_JSON))
               // .andDo(print())
                .andExpect(status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].employeeId").isNotEmpty());

    }
}