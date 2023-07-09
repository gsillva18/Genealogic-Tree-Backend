package com.example.genealogictree.repository;

import com.example.genealogictree.model.entitygenealogictree.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    public List<Person> findByName(String name);

}
