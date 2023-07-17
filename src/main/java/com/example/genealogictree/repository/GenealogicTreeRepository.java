package com.example.genealogictree.repository;

import com.example.genealogictree.model.entitygenealogictree.GenealogicTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenealogicTreeRepository extends JpaRepository<GenealogicTree, Integer> {

    @Query(value = "select count(*) from tb_genealogic_tree where account_gt_fk = :id and name= :name", nativeQuery = true)
    public Integer verificationByName(@Param("name") String name, @Param("id") Integer idAccount);

}
