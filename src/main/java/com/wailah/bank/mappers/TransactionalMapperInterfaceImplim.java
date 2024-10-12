package com.wailah.bank.mappers;

import com.wailah.bank.dtos.TransactionRequestDto;
import com.wailah.bank.dtos.TransactionResponseDto;
import com.wailah.bank.entities.Transactions;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TransactionalMapperInterfaceImplim implements TransactionalMapperInterface {
    @Override
    public Transactions transactionRequestToTransaction(TransactionRequestDto transactionRequestDto) {
        Transactions transaction = new Transactions();
        BeanUtils.copyProperties(transactionRequestDto,transaction);
        return transaction;
    }

    @Override
    public TransactionResponseDto transactionToTransactionResponse(Transactions transactions) {
        TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
        BeanUtils.copyProperties(transactions,transactionResponseDto);
        return transactionResponseDto;
    }
}
