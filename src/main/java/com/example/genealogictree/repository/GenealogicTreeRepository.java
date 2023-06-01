package com.example.genealogictree.repository;

import com.example.genealogictree.model.entitygenealogictree.GenealogicTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenealogicTreeRepository extends JpaRepository<GenealogicTree, Integer> {

    public List<GenealogicTree> findByName(String name);

}
