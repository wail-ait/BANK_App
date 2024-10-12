package com.wailah.bank.services;

import com.wailah.bank.dtos.TransactionRequestDto;
import com.wailah.bank.dtos.TransactionResponseDto;

import java.util.List;

public interface TransactionsServiceInterface {

    //get all the transactions
    public List<TransactionResponseDto> getAllTransactions();

    //method to get transaction by id
    public TransactionResponseDto getById(int id);

    //method to add new transaction
    public void addTransaction(TransactionRequestDto transactionRequestDto);

    //method to update an existing transaction
    public void updateTransaction(int id,TransactionRequestDto transactionRequestDto);

    //method to delete an existing transaction
    public void deleteTransaction(int id);

}
