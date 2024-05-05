package com.crypto.cryptocurrencycontroller.service;

import com.crypto.cryptocurrencycontroller.dto.external.CurrencyDataResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyApiServiceImplTest {
    private static final String BTCUSDT = "BTCUSDT";
    private static final String INVALID_CURRENCY = "M";
    @Autowired
    private CurrencyApiServiceImpl currencyApiService;

    @Test
    @DisplayName("Fetch rate of valid currency pair, returns valid dto")
    void fetchCurrencyRate_validPair_returnsValidDto() {
        CurrencyDataResponseDto btcData = currencyApiService.fetchCurrencyRate(BTCUSDT);
        Assertions.assertNotNull(btcData);
        Assertions.assertEquals(BTCUSDT, btcData.symbol());
        Assertions.assertNotNull(btcData.markPrice());
    }

    @Test
    @DisplayName("Fetch rate of invalid currency pair, returns exception")
    void fetchCurrencyRate_invalidCurrency_returnsException() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class,
                () -> currencyApiService.fetchCurrencyRate(INVALID_CURRENCY));
        String expected = "Invalid currency symbol. Please, enter valid data.";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }
}