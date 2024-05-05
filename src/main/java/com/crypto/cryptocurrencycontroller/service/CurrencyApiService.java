package com.crypto.cryptocurrencycontroller.service;

import com.crypto.cryptocurrencycontroller.dto.external.CurrencyDataResponseDto;

public interface CurrencyApiService {
    CurrencyDataResponseDto fetchCurrencyRate(String cryptoCurrency);
}
