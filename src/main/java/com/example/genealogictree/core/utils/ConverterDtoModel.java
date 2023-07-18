package com.example.genealogictree.core.utils;

import com.example.genealogictree.core.enums.TypePerson;
import com.example.genealogictree.dto.CreateGenealogicTreeDto;
import com.example.genealogictree.dto.CreatePersonDto;
import com.example.genealogictree.dto.ParentsAndChildrenDto;
import com.example.genealogictree.dto.UserGTDto;
import com.example.genealogictree.model.entityaccount.UserGT;
import com.example.genealogictree.model.entitygenealogictree.GenealogicTree;
import com.example.genealogictree.model.entitygenealogictree.Person;
import com.example.genealogictree.repository.PersonRepository;
import java.util.ArrayList;
import java.util.List;

public class ConverterDtoModel {

    public static UserGT convertUserGTDtoToUserGT(UserGTDto userGTDto){

        UserGT userGT = new UserGT();
        userGT.setIdUserAuth0(userGTDto.getIdUserAuth0());

        return userGT;
    }

    public static GenealogicTree convertGenealogictreeDtoToGenealogicTree(CreateGenealogicTreeDto genealogicTreeDto){

        GenealogicTree genealogicTree = new GenealogicTree();

        genealogicTree.setName(genealogicTreeDto.getName());

        return genealogicTree;
    }

    public static Person convertPersonDtoToPerson(CreatePersonDto personDto, PersonRepository personRepository){

        Person person = new Person();

        Person personFather = null;
        Person personMother = null;
        List<Person> biologicalChildren = new ArrayList<>();
        List<Person> adoptiveChildren = new ArrayList<>();
        List<Person> adoptiveParents = new ArrayList<>();

        for (ParentsAndChildrenDto itemPerson: personDto.getListParents()) {

            //busca person no banco, caso não encontre cadastra um novo
            Person personReturned = personRepository.findById(itemPerson.getIdPerson())
                    .orElse(new Person(itemPerson.getName(), true));

            if(itemPerson.getType() == TypePerson.BIOLOGIC_FATHER){
                personFather = personReturned;
            }else if(itemPerson.getType() == TypePerson.BIOLOGIC_MOTHER){
                personMother = personReturned;
            }else if(itemPerson.getType() == TypePerson.ADOPTIVE_FATHER_MOTHER){
                adoptiveParents.add(personReturned);
            }

        }

        for (ParentsAndChildrenDto itemPerson: personDto.getListChildren()) {

            Person personChildren = personRepository.findById(itemPerson.getIdPerson())
                    .orElse(new Person(itemPerson.getName(), true));

            if(itemPerson.getType() == TypePerson.BIOLOGIC_CHILDREN){
                biologicalChildren.add(personChildren);
            }else if(itemPerson.getType() == TypePerson.ADOPTIVE_CHILDREN){
                adoptiveChildren.add(personChildren);
            }
        }

        person.setName(personDto.getName());
        person.setBirth(personDto.getBirth());
        person.setActive(personDto.getIsActive());
        person.setImage(personDto.getImage());
        person.setBiologicalFather(personFather);
        person.setBiologicalMother(personMother);
        person.setBiologicalChildren(biologicalChildren);
        person.setAdoptiveChildren(adoptiveChildren);
        person.setAdoptiveParents(adoptiveParents);

        return person;
    }
















}
