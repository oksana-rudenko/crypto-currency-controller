package com.crypto.cryptocurrencycontroller.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.crypto.cryptocurrencycontroller.dto.external.CurrencyDataResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyApiServiceImpl implements CurrencyApiService {
    private static final String BASE_URL = "https://www.binance.com/fapi/v1/premiumIndex?symbol=%s";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;

    @Override
    public CurrencyDataResponseDto fetchCurrencyRate(String cryptoCurrency) {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BASE_URL.formatted(cryptoCurrency)))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request,
                    HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                return objectMapper.readValue(response.body(), CurrencyDataResponseDto.class);
            } else {
                throw new RuntimeException("Invalid currency symbol. Please, enter valid data.");
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
