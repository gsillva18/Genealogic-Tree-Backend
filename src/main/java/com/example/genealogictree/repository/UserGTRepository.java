package com.example.genealogictree.repository;

import com.example.genealogictree.model.entityaccount.UserGT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserGTRepository extends JpaRepository<UserGT, Integer> {

    public Optional<UserGT> findUserGTByIdUserAuth0(String idUserAuth0);

}
