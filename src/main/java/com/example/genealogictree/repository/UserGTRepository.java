package com.example.genealogictree.repository;

import com.example.genealogictree.model.entityaccount.UserGT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGTRepository extends JpaRepository<UserGT, Integer> {
}
