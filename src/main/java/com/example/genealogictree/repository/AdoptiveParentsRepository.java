package com.example.genealogictree.repository;

import com.example.genealogictree.model.entitygenealogictree.AdoptiveParents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptiveParentsRepository extends JpaRepository<AdoptiveParents, Integer> {
}
