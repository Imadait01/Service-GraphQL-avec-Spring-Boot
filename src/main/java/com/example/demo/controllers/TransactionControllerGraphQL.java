package com.example.demo.controllers;

import com.example.demo.entities.Compte;
import com.example.demo.entities.Transaction;
import com.example.demo.entities.TransactionRequest;
import com.example.demo.entities.TypeTransaction;
import com.example.demo.repositories.CompteRepository;
import com.example.demo.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class TransactionControllerGraphQL {
    private CompteRepository compteRepository;
    private TransactionRepository transactionRepository;

    @QueryMapping
    public List<Transaction> allTransactions() {
        return transactionRepository.findAll();
    }

    @QueryMapping
    public List<Transaction> compteTransactions(@Argument Long id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte not found"));
        return transactionRepository.findByCompte(compte);
    }

    @MutationMapping
    public Transaction addTransaction(@Argument TransactionRequest transaction) {
        Compte compte = compteRepository.findById(transaction.getCompteId())
                .orElseThrow(() -> new RuntimeException("Compte not found"));
        Transaction newTransaction = new Transaction();
        newTransaction.setMontant(transaction.getMontant());
        newTransaction.setDate(transaction.getDate());
        newTransaction.setType(transaction.getType());
        newTransaction.setCompte(compte);
        transactionRepository.save(newTransaction);
        return newTransaction;
    }

    @QueryMapping
    public Map<String, Object> transactionStats() {
        long count = transactionRepository.count();
        Double sumDepots = transactionRepository.sumByType(TypeTransaction.DEPOT);
        if (sumDepots == null)
            sumDepots = 0.0;
        Double sumRetraits = transactionRepository.sumByType(TypeTransaction.RETRAIT);
        if (sumRetraits == null)
            sumRetraits = 0.0;
        return Map.of(
                "count", count,
                "sumDepots", sumDepots,
                "sumRetraits", sumRetraits);
    }
}
