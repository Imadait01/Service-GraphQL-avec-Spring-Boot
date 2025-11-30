package com.example.demo.repositories;

import com.example.demo.entities.Compte;
import com.example.demo.entities.Transaction;
import com.example.demo.entities.TypeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCompte(Compte compte);

    @Query("select sum(t.montant) from Transaction t where t.type = :type")
    Double sumByType(@Param("type") TypeTransaction type);
}
