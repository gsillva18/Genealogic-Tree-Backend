package com.example.genealogictree.model.repository.people;

import com.example.genealogictree.model.entities.people.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {

    public List<People> findByName(String name);

}
