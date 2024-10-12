package com.wailah.bank.services;

import com.wailah.bank.dtos.TransactionRequestDto;
import com.wailah.bank.dtos.TransactionResponseDto;
import com.wailah.bank.entities.Accounts;
import com.wailah.bank.entities.TransactionType;
import com.wailah.bank.entities.Transactions;
import com.wailah.bank.mappers.TransactionalMapperInterface;
import com.wailah.bank.repositories.AccountsRepository;
import com.wailah.bank.repositories.TransactionsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionsServiceInterfaceImplim implements TransactionsServiceInterface {

    private final TransactionsRepository transactionsRepository;
    private final AccountsRepository accountsRepository;
    private final TransactionalMapperInterface transactionalMapperInterface;

    public TransactionsServiceInterfaceImplim(TransactionsRepository transactionsRepository, AccountsRepository accountsRepository, TransactionalMapperInterface transactionalMapperInterface) {
        this.transactionsRepository = transactionsRepository;
        this.accountsRepository = accountsRepository;
        this.transactionalMapperInterface = transactionalMapperInterface;
    }

    @Override
    public List<TransactionResponseDto> getAllTransactions() {
        List<Transactions> transactionsList = transactionsRepository.findAll();
        List<TransactionResponseDto> transactionResponseList = new ArrayList<>();
        for (Transactions transaction : transactionsList){
            transactionResponseList.add(transactionalMapperInterface.transactionToTransactionResponse(transaction));
        }
        return transactionResponseList;
    }

    @Override
    public TransactionResponseDto getById(int id) {
        Transactions transaction = transactionsRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Transaction not found !!"));
        return transactionalMapperInterface.transactionToTransactionResponse(transaction);
    }

    @Override
    public void addTransaction(TransactionRequestDto transactionRequestDto) {
        Transactions transaction = transactionalMapperInterface.transactionRequestToTransaction(transactionRequestDto);

        // Fetching the accounts involve in the transaction
        Accounts account = accountsRepository.findById(transactionRequestDto.getAccountId())
                .orElseThrow(()-> new RuntimeException("Account not found !!"));
        Accounts accountTarget=null;

        // Traiting all the transaction types
        switch (transactionRequestDto.getTransactionType()){
            case Deposit :
                {account.deposit(transactionRequestDto.getAmount());
                break;}
            case Withdrawal:
                {account.withdraw(transactionRequestDto.getAmount());
                break;}
            case Transfer:
            {   accountTarget = accountsRepository.findById(transactionRequestDto.getTargetAccountId())
                        .orElseThrow(()-> new RuntimeException("Account target not found !!"));
                account.withdraw(transactionRequestDto.getAmount());
                accountTarget.deposit(transactionRequestDto.getAmount());
                accountsRepository.save(accountTarget);
                break;}
        }

        accountsRepository.save(account);
        transactionsRepository.save(transaction);
    }

    @Override
    public void updateTransaction(int id, TransactionRequestDto transactionRequestDto) {

        // Fetching the original transaction
        Transactions originalTransaction = transactionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found !!"));

        // Fetching the involved account
        Accounts account = accountsRepository.findById(originalTransaction.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Accounts accountTarget = null;

        // Fetching the targeted account if it's a Transfer transaction
        if (originalTransaction.getTransactionType() == TransactionType.Transfer) {
            accountTarget = accountsRepository.findById(originalTransaction.getTargetAccountId())
                    .orElseThrow(() -> new RuntimeException("Target account not found !!"));
        }

        // Reverse the effect of the original transaction
        reverseTransactionEffect(originalTransaction, account, accountTarget);

        // Update the transaction details based on the new input
        if (transactionRequestDto.getTransactionType() != null)
            originalTransaction.setTransactionType(transactionRequestDto.getTransactionType());

        if (transactionRequestDto.getAmount() != 0)
            originalTransaction.setAmount(transactionRequestDto.getAmount());

        // Handle changes to accountId and targetAccountId
        if (transactionRequestDto.getAccountId() > 0 && transactionRequestDto.getAccountId() != originalTransaction.getAccountId()) {
            // If accountId has changed, fetch the new account and update
            account = accountsRepository.findById(transactionRequestDto.getAccountId())
                    .orElseThrow(() -> new RuntimeException("New account not found"));
            originalTransaction.setAccountId(transactionRequestDto.getAccountId());
        }

        if (transactionRequestDto.getTargetAccountId() > 0 && transactionRequestDto.getTargetAccountId() != originalTransaction.getTargetAccountId()) {
            // If targetAccountId has changed, fetch the new target account and update
            accountTarget = accountsRepository.findById(transactionRequestDto.getTargetAccountId())
                    .orElseThrow(() -> new RuntimeException("New target account not found"));
            originalTransaction.setTargetAccountId(transactionRequestDto.getTargetAccountId());
        }

        // Apply the new transaction effects
        applyTransactionEffect(originalTransaction, account, accountTarget);

        // Save the updated accounts and transaction
        accountsRepository.save(account);
        if (accountTarget != null) {
            accountsRepository.save(accountTarget);
        }

        transactionsRepository.save(originalTransaction);
    }

    // Helper method to reverse the effects of the original transaction
    private void reverseTransactionEffect(Transactions originalTransaction, Accounts account, Accounts accountTarget) {
        if (originalTransaction.getTransactionType() == TransactionType.Deposit) {
            account.withdraw(originalTransaction.getAmount());
        } else if (originalTransaction.getTransactionType() == TransactionType.Withdrawal) {
            account.deposit(originalTransaction.getAmount());
        } else if (originalTransaction.getTransactionType() == TransactionType.Transfer) {
            account.deposit(originalTransaction.getAmount()); // Reverse withdrawal from source
            if (accountTarget != null) {
                accountTarget.withdraw(originalTransaction.getAmount()); // Reverse deposit to target
            }
        }
    }

    // Helper method to apply the updated transaction effects
    private void applyTransactionEffect(Transactions transaction, Accounts account, Accounts accountTarget) {
        if (transaction.getTransactionType() == TransactionType.Deposit) {
            account.deposit(transaction.getAmount());
        } else if (transaction.getTransactionType() == TransactionType.Withdrawal) {
            account.withdraw(transaction.getAmount());
        } else if (transaction.getTransactionType() == TransactionType.Transfer) {
            account.withdraw(transaction.getAmount()); // Withdraw from source
            if (accountTarget != null) {
                accountTarget.deposit(transaction.getAmount()); // Deposit into target
            }
        }
    }


    @Override
    public void deleteTransaction(int id) {
        // Fetch the transaction to understand its details
        Transactions transaction = transactionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found !!"));

        Accounts account = accountsRepository.findById(transaction.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found !!"));

        Accounts accountTarget = null;

        // Reverse the effects of the transaction
        switch (transaction.getTransactionType()) {
            case Deposit:
                // Reverse a deposit by subtracting the amount
                account.withdraw(transaction.getAmount());
                break;
            case Withdrawal:
                // Reverse a withdrawal by adding the amount back
                account.deposit(transaction.getAmount());
                break;
            case Transfer:
                // Reverse a transfer: add to source, subtract from target
                accountTarget = accountsRepository.findById(transaction.getTargetAccountId())
                        .orElseThrow(() -> new RuntimeException("Target account not found !!"));
                account.deposit(transaction.getAmount());
                accountTarget.withdraw(transaction.getAmount());
                accountsRepository.save(accountTarget); // Save the target account update
                break;
        }

        // Save the updated account
        accountsRepository.save(account);

        // Now delete the transaction
        transactionsRepository.deleteById(id);
    }

}
