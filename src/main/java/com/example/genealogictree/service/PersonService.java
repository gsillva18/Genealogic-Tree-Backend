package com.example.genealogictree.service;

import com.example.genealogictree.core.exceptions.NotExistentEntityException;
import com.example.genealogictree.core.utils.ConverterDtoModel;
import com.example.genealogictree.dto.CreatePersonDto;
import com.example.genealogictree.model.entitygenealogictree.GenealogicTree;
import com.example.genealogictree.model.entitygenealogictree.Person;
import com.example.genealogictree.repository.GenealogicTreeRepository;
import com.example.genealogictree.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private GenealogicTreeRepository genealogicTreeRepository;

    public void createPerson(CreatePersonDto personDto) throws Exception{

        //verificar se árvore genealógica existe
        GenealogicTree genealogicTree = genealogicTreeRepository.findById(personDto.getIdGenealogicTree()).orElse(null);

        if(genealogicTree == null){
            throw new NotExistentEntityException();
        }

        //converter person dto para person
        Person person = ConverterDtoModel.convertPersonDtoToPerson(personDto, personRepository);

        //salvar a pessoa no banco de dados
        personRepository.save(person);

        //verificar se é a primeira pessoa da árvore
        if(genealogicTree.getInitialPerson() == null){
            genealogicTree.setInitialPerson(person);
            genealogicTreeRepository.save(genealogicTree);
        }



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
