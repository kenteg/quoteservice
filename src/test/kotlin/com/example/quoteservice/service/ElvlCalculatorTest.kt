package com.example.quoteservice.service

import com.example.quoteservice.model.Quote
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class ElvlCalculatorTest{

    @Test
    fun `when bid gt elvl then elvl is bid`(){
        val quote = Quote("test", BigDecimal.valueOf(7), BigDecimal.valueOf(100), BigDecimal.ONE)
        assertEquals(BigDecimal.valueOf(7), ElvlCalculator.mapElvl(quote))
    }

    @Test
    fun `when ask lt elvl then elvl is ask`(){
        val quote = Quote("test", BigDecimal.valueOf(7), BigDecimal.valueOf(100), BigDecimal.valueOf(101))
        assertEquals(BigDecimal.valueOf(100), ElvlCalculator.mapElvl(quote))
    }

    @Test
    fun `when elvl null then elvl is bid`(){
        val quote = Quote("test", BigDecimal.valueOf(7), BigDecimal.valueOf(100), null)
        assertEquals(BigDecimal.valueOf(7), ElvlCalculator.mapElvl(quote))
    }

    @Test
    fun `when bid is null then elvl is ask`(){
        val quote = Quote("test", null, BigDecimal.valueOf(100), null)
        assertEquals(BigDecimal.valueOf(100), ElvlCalculator.mapElvl(quote))
    }
}

//Правила расчёта elvl на основе котировки:
//1. Если bid > elvl, то elvl = bid
//2. Если ask < elvl, то elvl = ask
//3. Если значение elvl для данной бумаги отсутствует, то elvl = bid
//4. Если bid отсутствует, то elvl = ask