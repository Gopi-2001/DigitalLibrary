package com.minorProject.DigitalLibrary.repository;


import com.minorProject.DigitalLibrary.model.Txn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxnRepository extends JpaRepository<Txn,Integer> {
}
