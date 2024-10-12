package com.wailah.bank.web;

import com.wailah.bank.dtos.TransactionRequestDto;
import com.wailah.bank.dtos.TransactionResponseDto;
import com.wailah.bank.services.TransactionsServiceInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionsRestController {

    private final TransactionsServiceInterface transactionsServiceInterface;

    public TransactionsRestController(TransactionsServiceInterface transactionsServiceInterface) {
        this.transactionsServiceInterface = transactionsServiceInterface;
    }

    @GetMapping("/transaction")
    public List<TransactionResponseDto> getAll(){
        return transactionsServiceInterface.getAllTransactions();
    }

    @GetMapping("/transaction/{id}")
    public TransactionResponseDto getById(@PathVariable int id){
        return transactionsServiceInterface.getById(id);
    }

    @PostMapping("/transaction")
    public void add(@RequestBody TransactionRequestDto transactionRequestDto){
        transactionsServiceInterface.addTransaction(transactionRequestDto);
    }

    @PutMapping("/transaction/{id}")
    public void update(@PathVariable int id,@RequestBody TransactionRequestDto transactionRequestDto){
        transactionsServiceInterface.updateTransaction(id,transactionRequestDto);
    }

    @DeleteMapping("/transaction")
    public void delete(@PathVariable int id){
        transactionsServiceInterface.deleteTransaction(id);
    }
}
