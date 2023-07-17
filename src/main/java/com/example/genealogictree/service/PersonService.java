package com.example.genealogictree.service;

import com.example.genealogictree.dto.CreatePersonDto;
import com.example.genealogictree.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public void createPerson(CreatePersonDto personDto){



    }

    public void updatePerson(){

    }

    public void findPerson(){

    }

    public void findAllPerson(){

    }

    public void deletePerson(){

    }



}
