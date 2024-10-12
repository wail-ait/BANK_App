package com.wailah.bank.mappers;

import com.wailah.bank.dtos.TransactionRequestDto;
import com.wailah.bank.dtos.TransactionResponseDto;
import com.wailah.bank.entities.Transactions;

public interface TransactionalMapperInterface {

    public Transactions transactionRequestToTransaction(TransactionRequestDto transactionRequestDto);

    public TransactionResponseDto transactionToTransactionResponse(Transactions transactions);

}
