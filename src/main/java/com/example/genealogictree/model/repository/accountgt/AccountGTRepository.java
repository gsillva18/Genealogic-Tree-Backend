package com.example.genealogictree.model.repository.accountgt;

import com.example.genealogictree.model.entities.accountgt.AccountGT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountGTRepository extends JpaRepository<AccountGT, Integer> {

    public List<AccountGT> findByEmail(String email);

}

