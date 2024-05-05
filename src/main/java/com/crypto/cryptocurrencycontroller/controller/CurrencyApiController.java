package com.crypto.cryptocurrencycontroller.controller;

import com.crypto.cryptocurrencycontroller.dto.external.CurrencyDataResponseDto;
import com.crypto.cryptocurrencycontroller.dto.internal.CryptoCurrencyDto;
import com.crypto.cryptocurrencycontroller.service.CurrencyApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/crypto-currency")
public class CurrencyApiController {
    private final CurrencyApiService currencyApiService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CryptoCurrencyDto getCurrency(@RequestParam String symbol) {
        CurrencyDataResponseDto currencyResponseDto = currencyApiService.fetchCurrencyRate(symbol);
        return new CryptoCurrencyDto(currencyResponseDto.symbol(), currencyResponseDto.markPrice());
    }
}
