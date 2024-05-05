package com.crypto.cryptocurrencycontroller.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyApiControllerTest {
    private static final String URL_TEMPLATE = "/crypto-currency?symbol=";
    private static final String DOGEUSDT = "DOGEUSDT";
    private static MockMvc mockMvc;
    @Autowired
    private CurrencyApiController currencyApiController;

    @BeforeAll
    static void beforeAll(
            @Autowired WebApplicationContext applicationContext
    ) throws SQLException {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
    }

    @Test
    @DisplayName("Get rate of valid currency pair, returns valid currency dto")
    void getCurrency_validCurrency_returnsValidCurrencyDto() throws Exception {
        mockMvc.perform(get(URL_TEMPLATE + DOGEUSDT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.symbol").value(DOGEUSDT))
                .andExpect(jsonPath("$.markPrice").exists())
                .andReturn();
    }
}