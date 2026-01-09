package com.icodeap.bankingtransactions.infrastructure.web.dto;

import jakarta.validation.constraints.Positive;

public record WithdrawRequest(
       @Positive double amount
) {
}
