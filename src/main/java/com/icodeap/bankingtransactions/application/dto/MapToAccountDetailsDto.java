package com.icodeap.bankingtransactions.application.dto;

import com.icodeap.bankingtransactions.domain.model.Account;
import com.icodeap.bankingtransactions.domain.model.Transaction;

import java.util.List;

public class MapToAccountDetailsDto {

    public static AccountDetailsDto from(Account account){
        List<TransactionDto> transactionDto = account.getTransactions().stream().
                map(MapToAccountDetailsDto::mapTransaction)
                .toList();

        return new AccountDetailsDto(
                account.getId().value(),
                account.getCustomerId(),
                account.getBalance().getAmount().doubleValue(),
                transactionDto
        );
    }




    private static TransactionDto mapTransaction(Transaction transaction){
        return new TransactionDto(
                transaction.getId(),
                transaction.getTransactionType().name(),
                transaction.getAmount().getAmount().doubleValue()
        );
    }
}
