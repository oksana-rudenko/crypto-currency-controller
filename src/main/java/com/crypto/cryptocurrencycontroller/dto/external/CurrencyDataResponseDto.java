package com.crypto.cryptocurrencycontroller.dto.external;

import java.math.BigDecimal;

public record CurrencyDataResponseDto(
        String symbol,
        String markPrice,
        String indexPrice,
        String estimatedSettlePrice,
        String lastFundingRate,
        BigDecimal nextFundingTime,
        String interestRate,
        BigDecimal time
) {
}
