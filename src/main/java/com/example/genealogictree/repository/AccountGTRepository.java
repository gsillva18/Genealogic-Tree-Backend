package com.example.genealogictree.repository;

import com.example.genealogictree.model.entityaccount.AccountGT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountGTRepository extends JpaRepository<AccountGT, Integer> {

}

